package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

    Book findByTitle(String title);
    Book findByISBN(String isbn);

    List<Book> findByGenreId(Long id);

}
