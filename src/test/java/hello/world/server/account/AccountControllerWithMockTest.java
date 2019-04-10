package hello.world.server.account;

import hello.world.server.account.api.model.Account;
import hello.world.server.account.service.AccountService;
import hello.world.server.test.base.IntegrationTestBase;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

;

public class AccountControllerWithMockTest extends IntegrationTestBase{


    static AccountService accountService = createMock(AccountService.class);

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