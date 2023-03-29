package tim2.cataloging.tim2.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ShelfItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String review;

    @Column
    private String book;
}
