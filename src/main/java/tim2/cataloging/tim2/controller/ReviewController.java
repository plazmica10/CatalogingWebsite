package tim2.cataloging.tim2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.ReviewDto;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.service.ReviewService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

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
    @PostMapping("")
    public Review saveReview(@RequestBody Review review){
        // Set the date to the current date
        Date currentDate = new Date();
        review.setDate(currentDate);
        return reviewService.save(review);
    }
    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") Long id, @RequestBody Review updatedReview) {
        try {
            Review existingReview = reviewService.findOne(id);
            if (existingReview == null) {
                return ResponseEntity.notFound().build();
            }
            // Set the date to the current date
            Date currentDate = new Date();
            existingReview.setComment(updatedReview.getComment());
            existingReview.setRating(updatedReview.getRating());
            existingReview.setDate(currentDate);
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
