package Booktopia.Bibliotopia.repository;

import Booktopia.Bibliotopia.domain.Coupon;
import Booktopia.Bibliotopia.domain.CouponState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CouponRepositoryTest {
    @Autowired
    CouponRepository couponRepository;


    @Test
    public void 쿠폰_생성_등록_조회_테스트() {
        //given
        Coupon coupon = new Coupon();
        coupon.setName("testCoupon");
        coupon.setCouponState(CouponState.N);

        //when
        couponRepository.save(coupon);
        Coupon findCoupon = couponRepository.findById(coupon.getId()).get();

        //then
        assertThat(findCoupon.getId()).isEqualTo(coupon.getId());


    }

    @Test
    public void 쿠폰_수정_테스트() {
        //given
        Coupon coupon = new Coupon();
        coupon.setName("BeforeCoupon");
        coupon.setCouponState(CouponState.N);
        couponRepository.save(coupon);

        //when
        Coupon findCoupon = couponRepository.findById(coupon.getId()).get();
        findCoupon.setName("AfterCoupon");

        //then
        assertThat(couponRepository.findById(coupon.getId()).get().getName()).isEqualTo("AfterCoupon");
    }

    @Test
    public void 쿠폰_삭제_테스트() {
        //given
        Coupon coupon = new Coupon();
        coupon.setName("BeforeCoupon");
        coupon.setCouponState(CouponState.N);
        couponRepository.save(coupon);

        //when
        couponRepository.delete(coupon);

        //then
        assertThat(couponRepository.count()).isEqualTo(0);
    }
}
