package Booktopia.Bibliotopia.repository;


import Booktopia.Bibliotopia.domain.Book;
import Booktopia.Bibliotopia.domain.Item;
import Booktopia.Bibliotopia.domain.Member;
import Booktopia.Bibliotopia.domain.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderItemRepositoryTest {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ItemRepository itemRepository;


    @Test
    public void 주문_상품_생성_저장_조회_테스트() {
        //given
        Book book = new Book();
        book.setName("testBook");
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);

        itemRepository.save(book);

        //then
        orderItemRepository.saveAndFlush(orderItem);
        OrderItem findOrderItem = orderItemRepository.findById(orderItem.getId()).get();


        //then
        assertThat(findOrderItem.getItem().getName()).isEqualTo("testBook");

    }

    @Test
    public void 주문_상품_수정_테스트(){
        //given
        Book book = new Book();
        book.setName("testBook");
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);

        itemRepository.save(book);
        orderItemRepository.saveAndFlush(orderItem);
        //when
        OrderItem findOrderItem = orderItemRepository.findById(orderItem.getId()).get();
        findOrderItem.getItem().setName("afterTestBook");
        //then
        assertThat(orderItemRepository.findById(orderItem.getId()).get().getItem().getName()).isEqualTo("afterTestBook");

    }

    @Test
    public void 주문_상품_삭제_테스트() {
        //given
        Book book = new Book();
        book.setName("testBook");
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);

        itemRepository.save(book);
        orderItemRepository.saveAndFlush(orderItem);
        assertThat(orderItemRepository.count()).isEqualTo(1);
        //when
        orderItemRepository.delete(orderItem);
        assertThat(orderItemRepository.count()).isEqualTo(0);

    }


}
