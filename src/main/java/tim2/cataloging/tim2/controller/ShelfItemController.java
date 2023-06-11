package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.ShelfItemDto;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.model.ShelfItem;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.ReviewService;
import tim2.cataloging.tim2.service.ShelfItemService;
import tim2.cataloging.tim2.service.ShelfService;
import tim2.cataloging.tim2.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/shelfItems")
public class ShelfItemController {

    @Autowired
    private ShelfItemService shelfItemService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;


    //READ ALL
    @GetMapping("")
    public ResponseEntity<List<ShelfItem>> getShelfItems(){
        List<ShelfItem> shelfItems = shelfItemService.findAll();
        return ResponseEntity.ok(shelfItems);
    }

//READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ShelfItemDto> getShelfItem(@PathVariable Long id){
        ShelfItem shelfItem = shelfItemService.findById(id);
        if(shelfItem == null){
            return ResponseEntity.notFound().build();
        }

        ShelfItemDto shelfItemDto = new ShelfItemDto();
        shelfItemDto.setId(shelfItem.getId());
        shelfItemDto.setBook(shelfItem.getBook());
        shelfItemDto.setReviews(shelfItem.getReviews());

        return ResponseEntity.ok(shelfItemDto);
    }

    // PUT ON SHELF
    @PostMapping("/{shelfItemId}/{shelfId}")
    public ResponseEntity<Shelf> putOnShelf(@PathVariable(name = "shelfItemId") Long shelfItemId, @PathVariable(name = "shelfId") Long shelfId, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        Shelf shelf = shelfService.findOne(shelfId);
        ShelfItem shelfItem = shelfItemService.findById(shelfItemId);
        if (shelf == null || shelfItem == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        List<Shelf> shelves = loggedUser.getShelves();
        if (shelves.stream().noneMatch(s -> s.getId().equals(shelfId)))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        boolean allow = false;

        if (shelf.isPrimary()) {
            for (Shelf s : shelves) {
                if (s.isPrimary() && s.getShelfItems() != null) {
                    if (s.getShelfItems().stream().anyMatch(si -> si.getId().equals(shelfItemId)))
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
            }

            allow = true;
        }
        else {
            for (Shelf s : shelves) {
                if (s.isPrimary() && s.getShelfItems() != null) {
                    if (s.getShelfItems().stream().anyMatch(si -> si.getId().equals(shelfItemId))) {
                        allow = true;
                        break;
                    }
                }
            }
        }

        if (!allow)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        if (shelf.getShelfItems().contains(shelfItem))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        for (Shelf s : shelves) {
            if (Objects.equals(s.getId(), shelfId)) {
                if (s.getShelfItems() == null)
                    s.setShelfItems(new ArrayList<ShelfItem>());

                s.getShelfItems().add(shelfItem);

                shelfService.save(s);
                loggedUser.setShelves(shelves);
                userService.save(loggedUser);

                return ResponseEntity.ok(s);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // REMOVE FROM SHELF
    @DeleteMapping("/{shelfItemId}/{shelfId}")
    public ResponseEntity<Shelf> removeFromShelf(@PathVariable(name = "shelfItemId") Long shelfItemId, @PathVariable(name = "shelfId") Long shelfId, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        Shelf shelf = shelfService.findOne(shelfId);
        ShelfItem shelfItem = shelfItemService.findById(shelfItemId);
        if (shelf == null || shelfItem == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        List<Shelf> shelves = loggedUser.getShelves();
        if (shelves.stream().noneMatch(s -> s.getId().equals(shelfId)))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        if (shelf.getShelfItems() != null) {
            if (shelf.getShelfItems().stream().noneMatch(si -> si.getId().equals(shelfItemId)))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (shelf.isPrimary()) {
            for (Shelf s : shelves) {
                if (s.getShelfItems() != null) {
                    s.getShelfItems().removeIf(si -> si.getId().equals(shelfItemId));
                    shelfService.save(s);
                }
            }
        }
        else {
            for (Shelf s : shelves) {
                if (Objects.equals(s.getId(), shelfId)) {
                    if (s.getShelfItems() != null) {
                        s.getShelfItems().removeIf(si -> si.getId().equals(shelfItemId));
                        shelfService.save(s);
                    }
                }
            }
        }

        loggedUser.setShelves(shelves);
        userService.save(loggedUser);

        if (shelf.getName().equals("Read")) {
            if (shelfItem.getReviews() != null) {
//                shelfItem.getReviews().removeIf(r -> Objects.equals(r.getUser().getId(), loggedUser.getId()));
//                shelfItemService.save(shelfItem);

                for (Review r : shelfItem.getReviews()) {
                    if (Objects.equals(r.getUser().getId(), loggedUser.getId())) {
                        shelfItem.getReviews().remove(r);
                        shelfService.save(shelf);
                        reviewService.deleteById(r.getId());
                        break;
                    }
                }
            }
        }

        return ResponseEntity.ok(shelf);
    }
}
