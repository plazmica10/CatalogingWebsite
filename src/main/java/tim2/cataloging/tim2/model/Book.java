package tim2.cataloging.tim2.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Book implements Serializable{
    @Id
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
}
