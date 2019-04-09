package hello.world.server.account;

import hello.world.server.account.api.model.Account;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller("/accounts")
public class AccountController {

    @Inject
    AccountService accountService;

    @Get(value = "/id/{id}", processes = MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Account byId(String id) {

        final Account account = Account.builder()
                .id(id)
                .name(accountService.getName())
                .address1("70 Knock Pk")
                .number("+12321")
                .build();

        return account;
    }


    @Get("/ping")
    public String ping() {
        return "Hello world";
    }
//
//    @Get("/{id}")
//    public String byName(String id) {
//        return id;
//    }
}
