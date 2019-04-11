package hello.world.server.profile.api.operation;


import hello.world.server.profile.api.model.Profile;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

import static io.micronaut.http.MediaType.APPLICATION_JSON;


/**
 *
 *
 *
 */
@Validated
public interface ProfileOperations<T extends Profile> {

    @Get(value = "/{id}", processes = MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    T getById(String id);

    @Post(processes = MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    T create(@Valid @Body T profile);

    @Put(value = "/{id}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    T update(@PathVariable("id") String id,
             @Valid @Body T profile);


    @Patch(value = "/{id}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    T partialUpdate(@PathVariable("id") String id,
             @Valid @Body T profile);
}
