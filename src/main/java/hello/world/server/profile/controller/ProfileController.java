package hello.world.server.profile.controller;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.api.operation.ProfileOperations;
import hello.world.server.profile.service.ProfileService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import java.util.List;

@Controller("/profiles")
public class ProfileController implements ProfileOperations{

    ProfileService profileService;

    @Inject
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public String ping() {
        return "Hello world from profiles";
    }

    @Override
    public List list() {
        return profileService.list();
    }


    @Override
    public Profile byId(String id) {
        return profileService.fetchById(id);
    }

    @Override
    public Profile create(Profile profile) {
        return profileService.create(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return profileService.update(profile);
    }

    @Override
    public Profile partialUpdate(Profile profile) {
        return profileService.partialUpdate(profile);
    }

}
