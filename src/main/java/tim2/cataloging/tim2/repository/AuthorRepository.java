package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{
}
