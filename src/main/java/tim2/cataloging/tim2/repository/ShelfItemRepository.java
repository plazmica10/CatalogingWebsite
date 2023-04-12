package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.ShelfItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfItemRepository extends JpaRepository<ShelfItem, Long>{
}
