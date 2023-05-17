package tim2.cataloging.tim2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.model.Author;
import tim2.cataloging.tim2.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findOne(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

}
