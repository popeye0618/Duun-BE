package dunn.dunnshop.repository;

import dunn.dunnshop.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items,Long> {
}
