package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.Order;
import dunn.dunnshop.domain.OrderDetail;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.OrderDetailResponseDto;
import dunn.dunnshop.dto.OrderRequestDto;
import dunn.dunnshop.repository.ItemRepository;
import dunn.dunnshop.repository.OrderRepository;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShopController {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @PostMapping("users/save")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PostMapping("items/save")
    public void addItem(@RequestBody Item item) {
        itemRepository.save(item);
    }

    @PostMapping("orders/save")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = new Order();
        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + orderRequestDto.getUserId()));
        order.setUser(user);

        List<OrderDetail> orderDetails = orderRequestDto.getOrderDetails().stream()
                .map(dto -> {
                    Item item = itemRepository.findById(dto.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item not found: " + dto.getItemId()));
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setItem(item);
                    orderDetail.setQuantity(dto.getQuantity());
                    orderDetail.setOrder(order);
                    return orderDetail;
                })
                .toList();

        order.setOrderDetails(orderDetails);
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        //OrderDetail을 OrderDetailResponseDto로 바꿈
        List<OrderDetailResponseDto> orderDetailResponse = savedOrder.getOrderDetails().stream()
                .map(detail -> new OrderDetailResponseDto(detail.getId(), detail.getItem().getId(), detail.getQuantity()))
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", savedOrder.getId());
        response.put("orderDetails", orderDetailResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
