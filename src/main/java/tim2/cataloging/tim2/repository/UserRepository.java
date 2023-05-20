package tim2.cataloging.tim2.repository;

import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    User getByEmail(String email);

}
