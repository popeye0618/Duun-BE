package dunn.dunnshop.service;


import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.Order;
import dunn.dunnshop.domain.OrderDetail;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.OrderRequestDto;
import dunn.dunnshop.repository.ItemRepository;
import dunn.dunnshop.repository.OrderRepository;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public Order createOrder(OrderRequestDto orderRequestDto) {
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

        return orderRepository.save(order);
    }
}
