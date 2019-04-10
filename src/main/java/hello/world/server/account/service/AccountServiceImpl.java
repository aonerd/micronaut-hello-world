package hello.world.server.account.service;

import javax.inject.Singleton;

@Singleton
public class AccountServiceImpl implements AccountService{

    public String getName(){
        return "Name from account service";
    }
}
