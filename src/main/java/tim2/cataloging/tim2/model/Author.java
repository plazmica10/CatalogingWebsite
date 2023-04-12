package tim2.cataloging.tim2.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "authors")
public class Author extends User{
    @Column
    private boolean isActive;

    @OneToMany
    private List<Book> books;
}
