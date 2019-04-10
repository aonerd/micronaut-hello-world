package hello.world.server.profile.controller;

import hello.world.server.profile.api.model.Profile;
import hello.world.server.profile.api.operation.ProfileOperations;
import io.micronaut.http.client.annotation.Client;

@Client("/profiles")
interface ProfilesTestClient extends ProfileOperations<Profile> {
}

