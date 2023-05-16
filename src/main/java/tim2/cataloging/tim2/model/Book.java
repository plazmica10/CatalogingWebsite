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

    public Book(Long id, String ISBN, String title, String photo, Date date, int pages, String description, int rating, Genre genre) {
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
}
