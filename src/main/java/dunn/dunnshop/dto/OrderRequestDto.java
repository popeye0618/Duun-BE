package dunn.dunnshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderRequestDto {
    private Long userId;
    private List<OrderDetailRequestDto> orderDetails;
}
