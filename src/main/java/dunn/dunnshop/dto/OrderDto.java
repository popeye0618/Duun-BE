package dunn.dunnshop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderDto {
    private Long id;
    // 어떻게 해야될까???
    private Long userId;
    private LocalDateTime orderDate;

    public OrderDto(Long id, Long userId, LocalDateTime orderDate) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
    }
}
