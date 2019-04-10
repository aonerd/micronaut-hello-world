package hello.world.server.profile.controller;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.persistence.ProfileRepo;
import hello.world.server.test.base.IntegrationTestBase;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ProfileControllerTest extends IntegrationTestBase{


    public static final String PROFILES = "/profiles";
    ProfileRepo profileRepo = createMock(ProfileRepo.class);

//    @Test
//    void list() {
//    }

    @Test
    void testPing(){
        String response =  client.toBlocking().retrieve(PROFILES + "/ping");
        assertEquals("Hello world from profiles", response);
    }

    @Test
    void create() {
        Profile profile = Profile.builder()
                .forename("Aiden")
                .build();

        Profile profileRetuned = Profile.builder()
                .id(UUID.randomUUID())
                .forename(profile.getForename())
                .build();

        when(profileRepo.save(profile)).thenReturn(profileRetuned);

        ProfilesTestClient profileClient = this.server.getApplicationContext().getBean(ProfilesTestClient.class);

        final Profile profileSaved = profileClient.create(profile);

        //HttpResponse<Profile> response = profileClient.toBlocking().exchange(HttpRequest.POST(PROFILES, profile), Profile.class);
        //assertEquals(HttpStatus.OK, response.getStatus());

        assertEquals(profile.getForename(), profileSaved.getForename());
        assertNotNull(profileSaved.getId());

        final Profile profileFromGet = profileClient.byId(profileSaved.getId().toString());
        assertNotNull(profileFromGet);
        assertEquals(profileFromGet.getForename(), profile.getForename());
    }

    @Test
    void getTest() {
        HttpResponse<Profile> response = client.toBlocking().exchange(HttpRequest.GET(PROFILES +"/1"), Profile.class);
        final Profile profile = response.getBody().get();
        assertNotNull(profile);
    }


//    @Test
//    void update() {
//    }
//
//    @Test
//    void partialUpdate() {
//    }

}