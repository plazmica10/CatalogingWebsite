package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tim2.cataloging.tim2.dto.ActivationDto;
import tim2.cataloging.tim2.model.*;
import tim2.cataloging.tim2.service.ActivationRequestService;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.service.AuthorService;
import tim2.cataloging.tim2.service.ShelfService;
import tim2.cataloging.tim2.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class ActivationRequestController {

    @Autowired
    private ActivationRequestService requestService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ActivationDto> getReq(@PathVariable(name = "id") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.badRequest().body(null);

        if (loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.badRequest().body(null);

        ActivationRequest request = requestService.findOne(id);
        if (request == null)
            return ResponseEntity.badRequest().body(null);

        ActivationDto dto = new ActivationDto(request);

        return ResponseEntity.ok(dto);
    }
    @GetMapping("")
    public ResponseEntity<List<ActivationDto>> getReqs(HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.badRequest().body(null);
        if(loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.badRequest().body(null);

        List<ActivationRequest> requests = requestService.findAll();
        if (requests == null)
            return ResponseEntity.badRequest().body(null);

        List<ActivationDto> dtos = new ArrayList<>();
        for (ActivationRequest request : requests) {
            ActivationDto dto = new ActivationDto(request);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("")
    public ResponseEntity<String> send(@RequestBody ActivationDto request, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser != null)
            return ResponseEntity.badRequest().body("You cant send request while logged in!");

        ActivationRequest activationRequest = new ActivationRequest();
        Date date = new Date();
        activationRequest.setEmail(request.getEmail());
        activationRequest.setMessage(request.getMessage());
        activationRequest.setPhone(request.getPhone());
        activationRequest.setDate(date);
        activationRequest.setStatus(STATUS.PENDING);
        requestService.save(activationRequest);
        return ResponseEntity.ok("Request sent!");
    }
    //poziva je approve dugme
    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveRequest(@PathVariable(name = "id") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if(loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.badRequest().body("You are not admin!");

        ActivationRequest request = requestService.findOne(id);

        if (request == null)
            return ResponseEntity.badRequest().body("Request with id: " + id + " does not exist!");
        else {
            User user = userService.findByEmail(request.getEmail());
            if(user != null)
                return ResponseEntity.badRequest().body("User with email: " + request.getEmail() + " already exists!");

            request.setStatus(STATUS.APPROVED);
            requestService.save(request);

            Author author = new Author();
            author.setEmail(request.getEmail());
            author.setActive(true);
            Shelf wantToRead = new Shelf("Want to read",true);
            Shelf currentlyReading = new Shelf("Currently reading",true);
            Shelf read = new Shelf("Read",true);
            shelfService.save(wantToRead);
            shelfService.save(currentlyReading);
            shelfService.save(read);

            List<Shelf> shelves = new ArrayList<>();
            shelves.add(wantToRead);
            shelves.add(currentlyReading);
            shelves.add(read);

            author.setShelves(shelves);
            authorService.save(author);
            requestService.delete(id);
            return ResponseEntity.ok("Request approved!");
        }
    }
    //poziva je deny dugme
    @DeleteMapping("/{id}/deny")
    public ResponseEntity<String> deleteRequest(@PathVariable(name = "id") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if(loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.badRequest().body("You are not admin!");

        ActivationRequest request = requestService.findOne(id);

        if (request == null)
            return ResponseEntity.badRequest().body("Request with id: " + id + " does not exist!");
        else {
            requestService.delete(id);
            return ResponseEntity.ok("Request deleted!");
        }
    }
}
