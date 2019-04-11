package hello.world.server.profile.service;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.persistence.ProfileRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@Singleton
public class ProfileService {

    final HashMap<UUID, Profile> profileMap = new HashMap();

    ProfileRepo profileRepo;

    private final ModelMapper mapper;

    @Inject
    public ProfileService(ProfileRepo profileRepo) {

        this.profileRepo = profileRepo;
        mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    }

    public List list() {
        log.info("List called");
        return new ArrayList<>(profileMap.values());
    }

    public Profile fetchById(String id) {
        log.info("Getting profile by id[{}] ", id);
        return profileMap.get(UUID.fromString(id));
    }

    public Profile create(Profile profile) {
        log.info("Create profile [{}]", profile);
        final Profile savedProfile = profileRepo.save(profile);

        profileMap.put(savedProfile.getId(), savedProfile);

        return profileMap.get(savedProfile.getId());
    }

    public Profile update(Profile profile) {
        log.info("Update profile [{}]", profile);
        return profileMap.put(profile.getId(), profile);
    }

    public Profile partialUpdate(String id, Profile profile) {
        log.info("Patch profile [{}] [{}]", id, profile);

        final Profile currentProfile = profileMap.get(UUID.fromString(id));

        mapper.map(profile, currentProfile);
        profileMap.put(UUID.fromString(id), currentProfile);

        return currentProfile;
    }
}
