package tim2.cataloging.tim2.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "authors")
public class Author extends User{
    @Column
    private boolean isActive;

    @OneToMany
    private List<Book> books;

    public Author() {
    }

    public Author(String username, String name, String surname, String email, String password, LocalDate date, String photo, String description, ROLE role, boolean isActive) {
        super(username, name, surname, email, password, date, photo, description, role);
        this.isActive = isActive;
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
