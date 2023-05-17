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

    public Author() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
