package hello.world.server.account;

import hello.world.server.account.api.model.Account;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

;

public class AccountControllerWithMockTest {

    private static EmbeddedServer server;
    private static HttpClient client;


    static AccountService accountService = mock(AccountService.class);

    @BeforeAll
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .registerSingleton(AccountService.class, accountService)
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    public static void stopServer() {
        if(server != null) {
            server.stop();
        }
        if(client != null) {
            client.stop();
        }
    }

    @Test
    public void testPing(){
        String response =  client.toBlocking().retrieve("/accounts/ping");
        assertEquals("Hello world", response);
    }

    @Test
    public void testPing2(){
        HttpResponse response = client.toBlocking().exchange(HttpRequest.GET("/accounts/ping"), String.class);
        assertEquals("Hello world", response.getBody().get());
    }

    @Test
    public void testGetAccount(){
        when(accountService.getName()).thenReturn("Name from mocked service");
        HttpResponse<Account> response = client.toBlocking().exchange(HttpRequest.GET("/accounts/id/aon1"), Account.class);
        final Account account = response.getBody().get();
        assertNotNull(account);
        assertEquals("aon1", account.getId());
        assertEquals("Name from mocked service", account.getName());
    }
}