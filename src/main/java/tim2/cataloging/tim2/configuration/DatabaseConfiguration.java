package tim2.cataloging.tim2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.repository.ShelfRepository;

@Configuration
public class DatabaseConfiguration {

//    @Autowired
//    private ShelfRepository shelfRepository;
//
//    @Bean
//    public boolean instantiate(){
//        Shelf shelf = new Shelf("Shelf 1", false);
//
//        shelfRepository.save(shelf);
//
//        return true;
//    }
}
