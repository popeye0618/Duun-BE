package dunn.dunnshop.domain;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @Column(name="item_name",nullable = false,length = 50)
    private String itemName;

    @Column(name="item_Price",nullable = false)
    private Long itemPrice;

    @Column(name="item_description",length=100)
    private String itemDescription;

    @Column(name="item_category",nullable = false,length=20)
    private String itemCategory;

    @Builder
    private Items(String itemName, Long itemPrice, String itemDescription, String itemCategory) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }
}
