package tim2.cataloging.tim2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.repository.ShelfRepository;

import java.util.List;

@Service
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    public Shelf findOne(Long id){
        return shelfRepository.findById(id).orElse(null);
    }
    public List<Shelf> findAll(){
        return shelfRepository.findAll();
    }
    public Shelf save(Shelf shelf){
        return shelfRepository.save(shelf);
    }
    public void deleteById(Long id){
        shelfRepository.deleteById(id);
    }

}
