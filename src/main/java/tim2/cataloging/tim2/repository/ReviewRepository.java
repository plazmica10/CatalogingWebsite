package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
