package tim2.cataloging.tim2.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean isPrimary;

    @OneToMany
    private List<ShelfItem> shelfItems;
}
