package hello.world.server.profile.controller;

import hello.world.server.profile.api.client.ProfileClient;
import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.persistence.ProfileRepo;
import hello.world.server.test.base.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

class ProfileControllerTest extends IntegrationTestBase{

    private static final String PROFILES = "/profiles";
    ProfileRepo profileRepo = createMock(ProfileRepo.class);

    ProfileClient profileClient;

    @Override
    public void beforeEach(){
        profileClient = this.server.getApplicationContext().getBean(ProfileClient.class);
        reset(profileRepo);

    }


    @Test
    void createAndGetTest() {

        Profile profile = Profile.builder()
                .forename("Aiden")
                .build();

        Profile profileReturned = Profile.builder()
                .id(UUID.randomUUID())
                .forename(profile.getForename())
                .build();

        when(profileRepo.save(any(Profile.class))).thenReturn(profileReturned);

        final Profile profileSaved = profileClient.create(profile);

        //HttpResponse<Profile> response = profileClient.toBlocking().exchange(HttpRequest.POST(PROFILES, profile), Profile.class);
        //assertEquals(HttpStatus.OK, response.getStatus());

        assertEquals(profile.getForename(), profileSaved.getForename());
        assertNotNull(profileSaved.getId());

        final Profile profileFromGet = profileClient.getById(profileSaved.getId().toString());
        assertNotNull(profileFromGet);
        assertEquals(profileFromGet.getForename(), profile.getForename());
    }

    @Test
    void partialUpdateTest() {
        final String partial = "Partial";

        Profile profile = Profile.builder()
                .forename(partial)
                .build();

        Profile profileReturned = Profile.builder()
                .id(UUID.randomUUID())
                .forename(profile.getForename())
                .build();

        when(profileRepo.save(any(Profile.class))).thenReturn(profileReturned);

        final Profile profileSaved = profileClient.create(profile);

        assertNotNull(profileSaved);
        assertEquals(partial, profileSaved.getForename());
        assertNull(profileSaved.getAddress1());
        assertNull(profileSaved.getAddress2());
        assertNull(profileSaved.getMobileNumber());

        final Profile profilePatched1 = profileClient.partialUpdate(
                profileSaved.getId().toString(),
                Profile.builder()
                        .address1("Add1")
                        .address2("Add2")
                        .postcode("Pc1")
                        .build());


        assertNotNull(profilePatched1);
        assertEquals(profileSaved.getId(), profilePatched1.getId());
        assertEquals(partial, profilePatched1.getForename());
        assertEquals("Add1", profilePatched1.getAddress1());
        assertEquals("Add2", profilePatched1.getAddress2());
        assertNull(profilePatched1.getMobileNumber());


        final Profile profilePatched2 = profileClient.partialUpdate(
                profileSaved.getId().toString(),
                Profile.builder()
                        .address2("")
                        .postcode("Pc2")
                        .mobileNumber("+123213")
                        .build());


        //This is the only impact of this approach ie. unable to unset values
        //assertEquals("", profilePatched2.getAddress1());

        assertEquals("Pc2", profilePatched2.getPostcode());
        assertEquals("+123213", profilePatched2.getMobileNumber());
    }

}