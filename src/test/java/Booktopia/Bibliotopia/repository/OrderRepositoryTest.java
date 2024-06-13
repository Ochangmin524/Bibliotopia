package Booktopia.Bibliotopia.repository;


import Booktopia.Bibliotopia.domain.Book;
import Booktopia.Bibliotopia.domain.Order;
import Booktopia.Bibliotopia.domain.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
@Transactional
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ItemRepository itemRepository;


    @Test
    public void 주문_생성_저장_조회_테스트() {
        //given
        Book book = new Book();
        book.setName("testBook");
        itemRepository.save(book);
        Book book2 = new Book();
        book2.setName("testBook2");
        itemRepository.save(book2);


        OrderItem orderItem = new OrderItem();
        orderItem.setItem(book);
        orderItemRepository.save(orderItem);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItem(book2);
        orderItemRepository.save(orderItem2);




        Order order = new Order();
        order.getOrderItems().add(orderItem);
        order.getOrderItems().add(orderItem2);

        orderRepository.save(order);

        //then
        assertThat(orderRepository.count()).isEqualTo(1);



        Order findOrder = orderRepository.findById(order.getId()).get();


        //then
        assertThat(findOrder.getOrderItems().size()).isEqualTo(2);
        log.info("orderItems = {}",findOrder.getOrderItems().stream().toList());

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


