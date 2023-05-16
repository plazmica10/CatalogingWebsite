package tim2.cataloging.tim2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import tim2.cataloging.tim2.repository.BookRepository;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private BookRepository bookRepository;
}
