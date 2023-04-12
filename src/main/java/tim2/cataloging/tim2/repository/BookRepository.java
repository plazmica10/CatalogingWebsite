package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long>{
}
