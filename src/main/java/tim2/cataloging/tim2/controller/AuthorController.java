package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tim2.cataloging.tim2.model.Author;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

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
    public ResponseEntity<String> createAuthor(HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Author newAuthor = new Author();
        newAuthor.setActive(false);
        authorService.save(newAuthor);
        return ResponseEntity.ok().build();
    }

}
