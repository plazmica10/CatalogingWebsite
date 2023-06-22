package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.ShelfDto;
import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.ShelfService;
import tim2.cataloging.tim2.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private UserService userService;

    //READ ALL
    @GetMapping("")
    public ResponseEntity<List<ShelfDto>> getShelves(HttpSession session){
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Shelf> shelves = loggedUser.getShelves();

        List<ShelfDto> dtos = new ArrayList<>();
        for(Shelf s : shelves){
            ShelfDto dto = new ShelfDto(s);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    //READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getShelf(@PathVariable Long id, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Shelf shelf = shelfService.findOne(id);
        if(shelf == null){
            return ResponseEntity.notFound().build();
        }

        List<Shelf> shelves = loggedUser.getShelves();
        for (Shelf s : shelves) {
             if (s.getId().equals(id)) {
                return ResponseEntity.ok(shelf);
             }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    //CREATE
    @PostMapping("")
    public ResponseEntity<Shelf> saveShelf(@RequestBody Shelf shelf, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        shelf.setPrimary(false);
        shelf.setShelfItems(new ArrayList<>());
        for (Shelf s : loggedUser.getShelves()) {
            if (s.getName().equals(shelf.getName())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        try {
            loggedUser.getShelves().add(shelf);
            shelfService.save(shelf);
            userService.save(loggedUser);

            return ResponseEntity.ok(shelf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateShelf(@PathVariable("id") Long id, @RequestBody Shelf updatedShelf, HttpSession session) {
        try {
            User loggedUser = (User) session.getAttribute("user");
            if (loggedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
            }

            List<Shelf> shelves = loggedUser.getShelves();
            for (Shelf s : shelves) {
                if (s.getId().equals(id)) {
                    if (s.isPrimary()) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot update primary shelf");
                    }
                    s.setName(updatedShelf.getName());
                    shelfService.save(s);

                    return ResponseEntity.ok("Shelf " + id + " updated successfully");
                }
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have permission to update this shelf");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update shelf");
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelf(@PathVariable("id") Long id, HttpSession session) {
        try {
            User loggedUser = (User) session.getAttribute("user");
            if (loggedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
            }

            for (Shelf s : loggedUser.getShelves()) {
                if (s.getId().equals(id)) {
                    if (s.isPrimary()) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete primary shelf");
                    }
                    loggedUser.getShelves().remove(s);
                    userService.save(loggedUser);
                    shelfService.deleteById(id);
                    return ResponseEntity.ok("Shelf deleted successfully");
                }
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have permission to delete this shelf");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete shelf");
        }
    }

}