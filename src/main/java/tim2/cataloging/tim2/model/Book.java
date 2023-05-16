package tim2.cataloging.tim2.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ISBN;

    @Column
    private String title;

    @Column
    private String photo;

    @Column
    private Date date;

    @Column
    private int pages;

    @Column
    private String description;

    @Column
    private int rating;

    @ManyToOne
    private Genre genre;

    // CONSTRUCTORS
    public Book(String ISBN, String title, String photo, Date date, int pages, String description, int rating) {
        this.ISBN = ISBN;
        this.title = title;
        this.photo = photo;
        this.date = date;
        this.pages = pages;
        this.description = description;
        this.rating = rating;
    }

    public Book() {
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Book(){

    }
}
