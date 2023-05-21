package tim2.cataloging.tim2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tim2.cataloging.tim2.model.ActivationRequest;

public interface ActivationRequestRepository extends JpaRepository<ActivationRequest, Long> {
}
