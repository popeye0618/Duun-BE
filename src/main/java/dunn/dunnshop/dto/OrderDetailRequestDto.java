package dunn.dunnshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDetailRequestDto {
    private Long itemId;
    private int quantity;
}
