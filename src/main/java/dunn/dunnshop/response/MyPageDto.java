package dunn.dunnshop.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MyPageDto {
    private String userId;
    private String userName;
    private String phone;
    private String address;
    private String email;
}
