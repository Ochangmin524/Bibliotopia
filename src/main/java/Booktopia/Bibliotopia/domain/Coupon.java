package Booktopia.Bibliotopia.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_ID")
    private Long id;

    private String name;

    private double percent;

    private String couponCode;


    @Enumerated
    private CouponState couponState;


}

