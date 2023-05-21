package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.GenreDto;
import tim2.cataloging.tim2.model.Genre;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    //READ ALL
    @GetMapping("")
    public ResponseEntity<List<GenreDto>> getGenres(){
       List<Genre> genres = genreService.findAll();

       List<GenreDto> dtos = new ArrayList<>();
       for(Genre g : genres){
          GenreDto dto = new GenreDto(g);
          dtos.add(dto);
       }
       return ResponseEntity.ok(dtos);
    }

    //READ ONE
    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable(name = "id") Long id){
        return genreService.findOne(id);
    }

    //CREATE
    @PostMapping("")
    public ResponseEntity<String> saveGenre(@RequestBody Genre genre, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.badRequest().body("You must be logged in to add genres");

        if(loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.badRequest().body("Only admin can add genres");

        genreService.save(genre);
        return ResponseEntity.ok("Genre added successfully");
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenre(@PathVariable("id") Long id, @RequestBody Genre updatedGenre) {
        try {
            Genre existingGenre = genreService.findOne(id);
            if (existingGenre == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
            }
            existingGenre.setName(updatedGenre.getName());
            genreService.save(existingGenre); // Save the updated genre

            return ResponseEntity.ok("Genre " + id + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update genre");
        }
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable("id") Long id) {
        try {
            Genre genre = genreService.findOne(id);
            if (genre == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
            }
            genreService.deleteById(id);
            return ResponseEntity.ok("Genre " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete genre");
        }
    }
}
