package tim2.cataloging.tim2.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class ShelfItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(name = "item_reviews",
            joinColumns = @JoinColumn(name = "shelf_item_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private List<Review> reviews;

    @ManyToOne
    private Book book;
}
