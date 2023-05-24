package tim2.cataloging.tim2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.model.ShelfItem;
import tim2.cataloging.tim2.repository.ShelfItemRepository;
import tim2.cataloging.tim2.repository.ShelfRepository;

@Service
public class ShelfItemService {

//    @Autowired
//    private ShelfRepository shelfRepository;
//
//    public void deleteById(Long id) {
//        shelfRepository.deleteById(id);
//    }

    @Autowired
    private ShelfItemRepository shelfItemRepository;

    public ShelfItem findById(Long id) {
        return shelfItemRepository.findById(id).orElse(null);
    }

    public ShelfItem findByBookId(Long id) {
        return shelfItemRepository.findByBookId(id);
    }

    public ShelfItem save(ShelfItem shelfItem) {
        return shelfItemRepository.save(shelfItem);
    }

    public ShelfItem deleteById(Long id) {
        ShelfItem shelfItem = shelfItemRepository.findById(id).orElse(null);
        if (shelfItem != null) {
            shelfItemRepository.deleteById(id);
        }
        return shelfItem;
    }

}
