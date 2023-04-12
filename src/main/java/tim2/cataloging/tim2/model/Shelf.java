package tim2.cataloging.tim2.model;

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
    @JoinTable(name = "item_on_shelf",
            joinColumns = @JoinColumn(name = "shelf_id"),
            inverseJoinColumns = @JoinColumn(name = "shelfitem_id"))
    private List<ShelfItem> shelfItems;
}
