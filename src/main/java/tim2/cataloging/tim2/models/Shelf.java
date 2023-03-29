package tim2.cataloging.tim2.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean isPrimary;

    // todo povezati sa ShelfItem
}
