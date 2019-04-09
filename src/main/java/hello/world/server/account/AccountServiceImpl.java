package hello.world.server.account;

import javax.inject.Singleton;

@Singleton
public class AccountServiceImpl implements AccountService{

    public String getName(){
        return "Name from account service";
    }
}
