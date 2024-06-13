package Booktopia.Bibliotopia.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long totalPrice;



    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member; //객체 지향 모델링을 고려하여 객체 별개로 참조하기 쉽게 한다.

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> OrderItems = new ArrayList<>();


}
