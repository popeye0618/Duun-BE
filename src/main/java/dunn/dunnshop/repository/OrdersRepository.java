package dunn.dunnshop.repository;

import dunn.dunnshop.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository <Orders,Long>{
}
