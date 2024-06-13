package Booktopia.Bibliotopia.repository;

import Booktopia.Bibliotopia.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Book, Long> {

}
