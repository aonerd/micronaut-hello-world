package hello.world.server.profile.controller;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.api.operation.ProfileOperations;
import hello.world.server.profile.service.ProfileService;
import io.micronaut.http.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Controller("/profiles")
@Slf4j
public class ProfileController implements ProfileOperations{

    ProfileService profileService;

    @Inject
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public Profile getById(String id) {
        return profileService.fetchById(id);
    }

    @Override
    public Profile create(Profile profile) {
        return profileService.create(profile);
    }

    @Override
    public Profile update(String id, Profile profile){
        log.info("Update [{}] [{}]", id, profile);
        return profileService.fetchById(id);
    }

    @Override
    public Profile partialUpdate(String id, Profile profile){
        log.info("Patch [{}] [{}]", id, profile);
        return profileService.partialUpdate(id, profile);
    }

}
