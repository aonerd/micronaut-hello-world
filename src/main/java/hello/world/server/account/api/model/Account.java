package hello.world.server.account.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private String id;
    private String name;
    private String address1;
    private String address2;
    private String number;
}
