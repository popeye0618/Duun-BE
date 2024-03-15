package dunn.dunnshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
}
