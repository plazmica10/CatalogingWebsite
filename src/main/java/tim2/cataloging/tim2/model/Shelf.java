package tim2.cataloging.tim2.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "item_on_shelf",
            joinColumns = @JoinColumn(name = "shelf_id", unique = false),
            inverseJoinColumns = @JoinColumn(name = "shelfitem_id", unique = false))
    private List<ShelfItem> shelfItems;

    public Shelf() {
    }
    public Shelf(String name, boolean isPrimary) {
        this.name = name;
        this.isPrimary = isPrimary;
        shelfItems = new ArrayList<ShelfItem>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public List<ShelfItem> getShelfItems() {
        return shelfItems;
    }

    public void setShelfItems(List<ShelfItem> shelfItems) {
        this.shelfItems = shelfItems;
    }
}
