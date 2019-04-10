package hello.world.server.profile.api.operation;


import hello.world.server.profile.api.model.Profile;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.reactivex.Single;

import javax.validation.Valid;
import java.util.List;


/**
 *
 *
 *
 */
@Validated
public interface ProfileOperations<T extends Profile> {

    @Get("/ping")
    String ping();

    @Get()
    List<T> list();

    @Get(value = "/{id}", processes = MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    T byId(String id);

    @Post(processes = MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    T create(@Valid @Body T profile);

    @Put()
    T update(@Valid @Body T profile);

    @Patch()
    T partialUpdate(@Valid @Body T profile);
}
