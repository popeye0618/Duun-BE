package dunn.dunnshop.service;


import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.Order;
import dunn.dunnshop.domain.OrderDetail;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.dto.OrderRequestDto;
import dunn.dunnshop.repository.ItemRepository;
import dunn.dunnshop.repository.OrderRepository;
import dunn.dunnshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
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

//    public List<OrderDto> getOrders(Long id) {
//        Optional<Order> orders = orderRepository.findById(id);
//        List<OrderDto> orderDtos = orders.stream().map(order -> new OrderDto(order.getId(), order.getOrderDate()))
//                .collect(Collectors.toList());
//
//        return orderDtos;
//    }

    @Transactional
    public Optional<Order> getOrders(Long id) {
        return orderRepository.findById(id);
    }
}
