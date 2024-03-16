package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;
    @Column(name = "item_price", nullable = false)
    private Integer itemPrice;
    @Embedded
    private ItemDescription itemDescription;
    @Column(name = "item_category", nullable = false, length = 20)
    private String itemCategory;
    @Column(name = "img", nullable = false, length = 100)
    private String img;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
