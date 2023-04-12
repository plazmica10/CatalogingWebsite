package tim2.cataloging.tim2.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

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
    private Date date;

    @ManyToOne
    private User user;
}
