package hello.world.server.profile.service;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.persistence.ProfileRepo;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ProfileService {

    final HashMap<UUID, Profile> profileMap = new HashMap();

    ProfileRepo profileRepo;

    @Inject
    public ProfileService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public List list() {
        log.info("List called");
        return new ArrayList<>(profileMap.values());
    }

    public Profile fetchById(String id) {
        log.info("Getting profile by id[{}] ", id);
        return Profile.builder().build();
        //return profileMap.get(UUID.fromString(id));
    }

    public Profile create(Profile profile) {
        log.info("Create profile [{}]", profile);
        final Profile savedProfile = profileRepo.save(profile);

        profileMap.put(savedProfile.getId(), savedProfile);

        return savedProfile;
    }

    public Profile update(Profile profile) {
        log.info("Update profile [{}]", profile);
        return profileMap.put(profile.getId(), profile);
    }

    public Profile partialUpdate(Profile profile) {
        log.info("Patch profile [{}]", profile);
        return profileMap.put(profile.getId(), profile);
    }
}
