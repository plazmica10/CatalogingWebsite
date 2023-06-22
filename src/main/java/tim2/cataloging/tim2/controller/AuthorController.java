package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.AuthorDto;
import tim2.cataloging.tim2.model.Author;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController{

    @Autowired
    private AuthorService authorService;


    @GetMapping("")
    public ResponseEntity<List<Author>> getAuthors(HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(authorService.findAll());
    }
    @PostMapping("")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDto autor, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Author newAuthor = new Author();
        newAuthor.setActive(false);
        newAuthor.setRole(ROLE.AUTHOR);
        newAuthor.setName(autor.getName());
        newAuthor.setSurname(autor.getSurname());
        newAuthor.setUsername(autor.getUsername());
        authorService.save(newAuthor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable(name = "id") Long id, @RequestBody Author author, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Author a = authorService.findOne(id);
        if (a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(a.isActive()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(author.getName() != null)
            a.setName(author.getName());
        if(author.getSurname() != null)
            a.setSurname(author.getSurname());
        if(author.getDescription() != null)
            a.setDescription(author.getDescription());
        if(author.getUsername() != null)
            a.setUsername(author.getUsername());
        authorService.save(a);
        return ResponseEntity.ok(a);
    }

}
