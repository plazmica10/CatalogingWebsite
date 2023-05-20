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
public class ShelfControler {

    @Autowired
    private ShelfService shelfService;

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
    public Shelf getShelf(@PathVariable Long id){
        return shelfService.findOne(id);
    }

    //CREATE
    @PostMapping("")
    public Shelf saveShelf(@RequestBody Shelf shelf){
        return shelfService.save(shelf);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateShelf(@PathVariable("id") Long id, @RequestBody Shelf updatedShelf) {
        try {
            Shelf existingShelf = shelfService.findOne(id);
            if (existingShelf == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelf not found");
            }
            existingShelf.setName(updatedShelf.getName());
            shelfService.save(existingShelf); // Save the updated shelf

            return ResponseEntity.ok("Shelf " + id + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update shelf");
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelf(@PathVariable("id") Long id) {
        try {
            Shelf shelf = shelfService.findOne(id);
            if (shelf == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shelf not found");
            }
            shelfService.deleteById(id);
            return ResponseEntity.ok("Shelf deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete shelf");
        }
    }

}
