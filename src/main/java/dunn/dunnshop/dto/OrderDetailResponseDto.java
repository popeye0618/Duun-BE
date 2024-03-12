package dunn.dunnshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class OrderDetailResponseDto {
    private Long id;
    private Long itemId;
    private int quantity;
}
