package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long>{
}
