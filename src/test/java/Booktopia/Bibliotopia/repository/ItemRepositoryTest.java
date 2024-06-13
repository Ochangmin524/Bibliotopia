package Booktopia.Bibliotopia.repository;

import Booktopia.Bibliotopia.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

//    @PersistenceContext
//    EntityManager em;

    @Test
    public void 북_생성_저장_조회_테스트() {
        //given
        Book book = new Book();
        book.setName("testBook");

        //when
        itemRepository.saveAndFlush(book);


        //then
        Book findBook = itemRepository.findById(book.getId()).get();
        assertThat(findBook.getId()).isEqualTo(book.getId());
    }

    @Test
    public void 북_수정_테스트() {
        //given
        Book book = new Book();
        book.setName("testBookBeforeEdit");

        itemRepository.save(book);
        //when

        Book findbook = itemRepository.findById(book.getId()).get();
        findbook.setName("testBookAfterEdit");

        //then
        assertThat(itemRepository.findById(book.getId()).get().getName()).isEqualTo("testBookAfterEdit");


    }

    @Test
    public void 북_삭제_테스트() {
        //given
        Book book = new Book();
        book.setName("testBookBeforeEdit");
        itemRepository.save(book);
        //when
        itemRepository.delete(book);
        //then
        assertThat(itemRepository.count()).isEqualTo(0);
    }
}
