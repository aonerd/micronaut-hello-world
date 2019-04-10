package hello.world.server.profile.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    private UUID id;

    private String forename;
    private String surname;

    private String address1;
    private String address2;
    private String city;
    private String postcode;

    private String mobileNumber;
    private String phoneNumber;

    private String dob;
    private String gender;
}
