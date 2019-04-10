package hello.world.server.account;

import hello.world.server.account.api.model.Account;
import hello.world.server.test.base.IntegrationTestBase;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;;

public class AccountControllerTest extends IntegrationTestBase{

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
        HttpResponse<Account> response = client.toBlocking().exchange(HttpRequest.GET("/accounts/id/aon1"), Account.class);
        final Account account = response.getBody().get();
        assertNotNull(account);
        assertEquals("aon1", account.getId());
        assertEquals("Name from account service", account.getName());
    }
}