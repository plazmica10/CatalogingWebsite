package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tim2.cataloging.tim2.model.ActivationRequest;
import tim2.cataloging.tim2.model.Author;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.ActivationRequestService;
import tim2.cataloging.tim2.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ActivationRequestService requestService;

//    @PostMapping("/{requestId}/accept")
//    public ResponseEntity createAuthor(@PathVariable(name = "requestId") Long requestId, HttpSession session) {
//        User loggedUser = (User) session.getAttribute("user");
//        if (loggedUser == null)
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        if (loggedUser.getRole() != ROLE.ADMIN)
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//
//        ActivationRequest request = requestService.findOne(requestId);
//        if (request == null)
//            return ResponseEntity.badRequest().body(null);
//
//        User user = new User();
//        user.setEmail(request.getEmail());
//
//        Author author = new Author();
//
//    }

}
