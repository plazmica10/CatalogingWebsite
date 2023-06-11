package tim2.cataloging.tim2.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import tim2.cataloging.tim2.model.Book;
import tim2.cataloging.tim2.model.Genre;

import java.util.Date;
import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookDto {

    private Long id;

    private String ISBN;

    private String title;

    private String photo;

    private LocalDate date;

    private int pages;

    private String description;

    private int rating;

    private Genre genre;

    // CONSTRUCTORS
    public BookDto(Long id, String ISBN, String title, String photo, LocalDate date, int pages, String description, int rating, Genre genre) {
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.photo = photo;
        this.date = date;
        this.pages = pages;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.ISBN = book.getISBN();
        this.title = book.getTitle();
        this.photo = book.getPhoto();
        this.date = book.getDate();
        this.pages = book.getPages();
        this.description = book.getDescription();
        this.rating = book.getRating();
        this.genre = book.getGenre();
    }
}
