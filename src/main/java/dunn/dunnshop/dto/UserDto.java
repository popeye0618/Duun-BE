package dunn.dunnshop.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private String email;
    private String id;

    public UserDto(String email,String id) {
        this.email = email;
        this.id = id;
    }
}
