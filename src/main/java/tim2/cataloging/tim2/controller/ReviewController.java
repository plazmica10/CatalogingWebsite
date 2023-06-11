package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.ReviewDto;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.service.BookService;
import tim2.cataloging.tim2.service.ReviewService;
import tim2.cataloging.tim2.model.*;
import tim2.cataloging.tim2.service.ShelfItemService;
import tim2.cataloging.tim2.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShelfItemService shelfItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    //READ ALL
    @GetMapping("")
    public ResponseEntity<List<ReviewDto>> getReviews(){
        List<Review> reviews = reviewService.findAll();

        List<ReviewDto> dtos = new ArrayList<>();
        for(Review r : reviews){
           ReviewDto dto = new ReviewDto(r);
           dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
    //READ ONE
    @GetMapping("/{id}")
    public Review getReview(@PathVariable(name = "id") Long id){
        return reviewService.findOne(id);
    }

    //CREATE
    @PostMapping("/{bookId}")
    public ResponseEntity<Review> saveReview(@RequestBody ReviewDto reviewDto, @PathVariable(name = "bookId") Long bookId, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        boolean allow = false;
        ShelfItem shelfItem = new ShelfItem();
        List<Shelf> shelves = loggedUser.getShelves();
        for (Shelf s : shelves) {
            if (s.getName().equals("Read")) {
                List<ShelfItem> shelfItems = s.getShelfItems();
                if (shelfItems != null) {
                    for (ShelfItem si : shelfItems) {
                        if (si.getBook().getId().equals(bookId)) {
                            allow = true;
                            shelfItem = si;
                            break;
                        }
                    }
                }

                break;
            }
        }

        if (!allow) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        for(Review r : shelfItem.getReviews()){
           if(r.getUser().getId() == loggedUser.getId())
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Review review = new Review();

        // Set the date to the current date
        review.setDate(LocalDate.now());
        review.setUser(loggedUser);
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());

        if (shelfItem.getReviews() == null)
            shelfItem.setReviews(new ArrayList<>());

        shelfItem.getReviews().add(review);
        reviewService.save(review);
        shelfItemService.save(shelfItem);
        userService.save(loggedUser);

        double rating = 0;
        for (Review r : shelfItem.getReviews()) {
            rating += r.getRating();
        }
        rating /= shelfItem.getReviews().size();
        Book book = bookService.findOne(bookId);
        book.setRating((int) rating);
        bookService.save(book);
        shelfItemService.save(shelfItem);
        return ResponseEntity.ok(review);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") Long id, @RequestBody ReviewDto updatedReview, HttpSession session) {
        try {
            User loggedUser = (User) session.getAttribute("user");
            if (loggedUser == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in");

            Review existingReview = reviewService.findOne(id);
            if (existingReview == null) {
                return ResponseEntity.notFound().build();
            }
            if (existingReview.getUser().getId() != loggedUser.getId())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not the owner of this review");

            // Set the date to the current date
            Date currentDate = new Date();
            if (updatedReview.getComment() != null)
                existingReview.setComment(updatedReview.getComment());
            if (updatedReview.getRating() != 0)
                existingReview.setRating(updatedReview.getRating());

            existingReview.setDate(LocalDate.now());
            reviewService.save(existingReview); // Save the updated review

            return ResponseEntity.ok("Review " + id + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update review");
        }
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long id) {
        try {
            Review review = reviewService.findOne(id);
            if (review == null) {
                return ResponseEntity.status(ResponseEntity.notFound().build().getStatusCode()).body("Review not found");
            }
            reviewService.deleteById(id);
            return ResponseEntity.ok("Review " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete review");
        }
    }
}
