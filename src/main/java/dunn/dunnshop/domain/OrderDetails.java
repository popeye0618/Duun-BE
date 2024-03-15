package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본생성자
@Table(name="order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Items items;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Orders orders;

    // nullable yes or no
    private Long quantity;

    @Builder
    private OrderDetails(Long quantity) {
        this.quantity = quantity;
    }

    //편의성 메서드
    public void setOrders(Orders orders){
        this.orders = orders;
        orders.addOrderDetailsList(this);
    }

}
