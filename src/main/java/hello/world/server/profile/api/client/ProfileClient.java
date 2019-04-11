package hello.world.server.profile.api.client;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.api.operation.ProfileOperations;
import io.micronaut.http.client.annotation.Client;


@Client("/profiles")
public interface ProfileClient extends ProfileOperations<Profile> {
}

