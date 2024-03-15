package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Order;
import dunn.dunnshop.dto.OrderDetailResponseDto;
import dunn.dunnshop.dto.OrderRequestDto;
import dunn.dunnshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order savedOrder = orderService.createOrder(orderRequestDto);

        //OrderDetail을 OrderDetailResponseDto로 바꿈
        List<OrderDetailResponseDto> orderDetailResponse = savedOrder.getOrderDetails().stream()
                .map(detail -> new OrderDetailResponseDto(detail.getId(), detail.getItem().getId(), detail.getQuantity()))
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", savedOrder.getId());
        response.put("orderDetails", orderDetailResponse);

        return response;
    }

}
