package Booktopia.Bibliotopia.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "MEMBER_ID")
    public Long id;

    public String name;

    public String address;

    public Long point;

    public String email;

    @OneToMany(cascade = CascadeType.REMOVE)
    public List<Coupon> coupons = new ArrayList<>();

}
