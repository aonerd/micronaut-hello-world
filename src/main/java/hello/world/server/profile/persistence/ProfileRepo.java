package hello.world.server.profile.persistence;

import hello.world.server.profile.api.model.Profile;

import javax.inject.Singleton;

@Singleton
public class ProfileRepo {

    public Profile save(Profile profile){
        return profile;
    }
}
