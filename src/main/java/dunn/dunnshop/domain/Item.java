package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;
    @Column(name = "item_price", nullable = false)
    private Integer itemPrice;
    @Column(name = "item_description", length = 100)
    private String itemDescription;
    @Column(name = "item_category", nullable = false, length = 20)
    private String itemCategory;
}
