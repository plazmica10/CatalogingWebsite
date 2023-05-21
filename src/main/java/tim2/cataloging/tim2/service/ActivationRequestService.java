package tim2.cataloging.tim2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.model.ActivationRequest;
import tim2.cataloging.tim2.repository.ActivationRequestRepository;

import java.util.List;

@Service
public class ActivationRequestService {

    @Autowired
    private ActivationRequestRepository requestRepository;

    public ActivationRequest findOne(Long id) {
        return requestRepository.findById(id).orElse(null);
    }
    public List<ActivationRequest> findAll() {
        return requestRepository.findAll();
    }
    public ActivationRequest save(ActivationRequest request) {
        return requestRepository.save(request);
    }
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
