package tim2.cataloging.tim2.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import tim2.cataloging.tim2.model.Review;
import tim2.cataloging.tim2.model.User;

import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewDto {
    private Long id;

    private int rating;

    private String comment;

    private Date date;

    private User user;

    public ReviewDto(){

    }
    public ReviewDto(Long id, int rating, String comment, Date date, User user) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.user = user;
    }
    public ReviewDto(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.date = review.getDate();
        this.user = review.getUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
