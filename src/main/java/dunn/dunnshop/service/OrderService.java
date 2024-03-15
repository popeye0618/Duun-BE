package dunn.dunnshop.service;

import dunn.dunnshop.domain.Orders;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.OrderDto;
import dunn.dunnshop.repository.OrdersRepository;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    public void save(Orders orders){
        ordersRepository.save(orders);
    }
    public void create(User user){
        Orders orders = Orders.builder().user(user).orderDate(LocalDateTime.now()).build();
        save(orders);
    }

    public List<OrderDto> findAllOrders(){
        List<Orders> allOrders =  ordersRepository.findAll();
        return allOrders.stream().map(o->new OrderDto(o.getId(),
                                                        o.getUser().getId(),
                                                        o.getOrderDate())).toList();
    }

}
