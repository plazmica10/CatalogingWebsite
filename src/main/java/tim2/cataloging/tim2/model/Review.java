package tim2.cataloging.tim2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int rating;

    @Column
    private String comment;

    @Column
    private LocalDate date;

    @ManyToOne
    private User user;

    public Review(){

    }
    public Review(Long id, int rating, String comment, LocalDate date) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Review(int rating, String comment, LocalDate date, User user) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.user = user;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
