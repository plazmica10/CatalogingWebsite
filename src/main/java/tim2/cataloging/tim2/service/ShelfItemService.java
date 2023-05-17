package tim2.cataloging.tim2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.repository.ShelfRepository;

@Service
public class ShelfItemService {

    @Autowired
    private ShelfRepository shelfRepository;

    public void deleteById(Long id) {
        shelfRepository.deleteById(id);
    }

}
