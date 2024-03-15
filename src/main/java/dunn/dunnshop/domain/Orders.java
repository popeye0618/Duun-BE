package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본생성자
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="order_data",nullable = false)
    private LocalDateTime orderDate;

    @OneToMany(mappedBy ="orders",cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();

    @Builder  //@setter 대용
    private Orders(User user, LocalDateTime orderDate) {
        this.user= user;
        this.orderDate = orderDate;
    }

    //편의성 메서드
    public void addOrderDetailsList(OrderDetails orderDetails){
        orderDetailsList.add(orderDetails);
    }
}
