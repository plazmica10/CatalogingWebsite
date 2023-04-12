package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
