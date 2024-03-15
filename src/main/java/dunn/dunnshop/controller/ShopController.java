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
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("users")
    public ResponseEntity getUser() {
        List<String> usernames = userRepository.findAll().stream()
                .map(user -> user.getUsername()).toList();
        log.info("userList = {}", usernames);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("users/save")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("users/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        BeanUtils.copyProperties(updateUser, findUser, "id");
        userRepository.save(findUser);

        return ResponseEntity.status(HttpStatus.OK).body("Update Success!!");
    }

    @GetMapping("items")
    public ResponseEntity<String> getItem() {
        List<String> items = itemRepository.findAll().stream()
                .map(item -> item.getItemName()).toList();
        log.info("userList = {}", items);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found: " + id));

        userRepository.delete(findUser);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!");
    }

    @PostMapping("items/update/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody Item updateItem) {
        Item findItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id));

        BeanUtils.copyProperties(updateItem, findItem, "id");
        itemRepository.save(findItem);
        return ResponseEntity.status(HttpStatus.OK).body("Update Success!!");
    }

    @PostMapping("items/save")
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("items/{id}")
    public ResponseEntity<String> removeItem(@PathVariable Long id) {
        Item findItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found: " + id));

        itemRepository.delete(findItem);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!");
    }

    @PostMapping("orders")
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
