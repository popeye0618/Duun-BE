package dunn.dunnshop.dto.verify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class VerifyDto {

    private String email;
    private String code;
}
