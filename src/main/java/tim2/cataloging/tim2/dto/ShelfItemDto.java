package tim2.cataloging.tim2.dto;

import tim2.cataloging.tim2.model.Book;
import tim2.cataloging.tim2.model.Review;

import java.util.List;

public class ShelfItemDto {
    private Long id;

    private List<Review> reviews;

    private Book book;

    public ShelfItemDto(Long id, List<Review> reviews, Book book) {
        this.id = id;
        this.reviews = reviews;
        this.book = book;
    }

    public ShelfItemDto(List<Review> reviews, Book book) {
        this.reviews = reviews;
        this.book = book;
    }

    public ShelfItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
