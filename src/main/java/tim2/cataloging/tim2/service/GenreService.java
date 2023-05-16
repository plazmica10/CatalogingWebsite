package tim2.cataloging.tim2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim2.cataloging.tim2.model.Genre;
import tim2.cataloging.tim2.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre findOne(Long id){
        return genreRepository.findById(id).orElse(null);
    }
    public List<Genre> findAll(){
        return genreRepository.findAll();
    }
    public Genre save(Genre genre){
        return genreRepository.save(genre);
    }
    public void deleteById(Long id){ genreRepository.deleteById(id);}
}
