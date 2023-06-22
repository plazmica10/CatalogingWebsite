package tim2.cataloging.tim2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import tim2.cataloging.tim2.model.*;
import tim2.cataloging.tim2.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShelfItemRepository shelfItemRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Bean
    public boolean instantiate() {
        Genre g1 = new Genre("Horror");
        Genre g2 = new Genre("Comedy");
        Genre g3 = new Genre("Drama");
        Genre g4 = new Genre("Action");
        Genre g5 = new Genre("Fantasy");
        Genre g6 = new Genre("Thriller");

        genreRepository.save(g1);
        genreRepository.save(g2);
        genreRepository.save(g3);
        genreRepository.save(g4);
        genreRepository.save(g5);
        genreRepository.save(g6);

        // READERS
        List<Shelf> shelves;

        Shelf s11 = new Shelf("Want to read", true);
        Shelf s12 = new Shelf("Currently reading", true);
        Shelf s13 = new Shelf("Read", true);
        shelfRepository.save(s11);
        shelfRepository.save(s12);
        shelfRepository.save(s13);
        User u1 = new User("test", "John", "Doe", "john@gmail.com", "123", LocalDate.of(1913, 6, 9), "photo", "desc", ROLE.READER);
        shelves = new ArrayList<>();
        shelves.add(s11);
        shelves.add(s12);
        shelves.add(s13);
        u1.setShelves(shelves);
        userRepository.save(u1);

        Shelf s21 = new Shelf("Want to read", true);
        Shelf s22 = new Shelf("Currently reading", true);
        Shelf s23 = new Shelf("Read", true);
        shelfRepository.save(s21);
        shelfRepository.save(s22);
        shelfRepository.save(s23);
        User u2 = new User("user", "Mike", "Oxlong", "mikey@gmail.com", "123", LocalDate.of(1913, 6, 9), "photo", "desc", ROLE.READER);
        shelves = new ArrayList<>();
        shelves.add(s21);
        shelves.add(s22);
        shelves.add(s23);
        u2.setShelves(shelves);
        userRepository.save(u2);

        // AUTHORS
        Shelf s31 = new Shelf("Want to read", true);
        Shelf s32 = new Shelf("Currently reading", true);
        Shelf s33 = new Shelf("Read", true);
        shelfRepository.save(s31);
        shelfRepository.save(s32);
        shelfRepository.save(s33);
        Author auth1 = new Author("daisyMeRollin", "Joanne", "Rowling", "misBigJ@gmail.com", "pass123", LocalDate.of(1913, 6, 9), "photo", "desc", ROLE.AUTHOR, true);
        shelves = new ArrayList<>();
        shelves.add(s31);
        shelves.add(s32);
        shelves.add(s33);
        auth1.setShelves(shelves);
        authorRepository.save(auth1);

        Shelf s41 = new Shelf("Want to read", true);
        Shelf s42 = new Shelf("Currently reading", true);
        Shelf s43 = new Shelf("Read", true);
        shelfRepository.save(s41);
        shelfRepository.save(s42);
        shelfRepository.save(s43);
        Author auth2 = new Author("tolkien", "John", "Ronald Reuel Tolkien", "tolkien@gmail.com", "pass123", LocalDate.of(1965, 7, 31), "photo", "desc", ROLE.AUTHOR, true);
        shelves = new ArrayList<>();
        shelves.add(s41);
        shelves.add(s42);
        shelves.add(s43);
        auth2.setShelves(shelves);
        authorRepository.save(auth2);

        // ADMINS
        User admin1 = new User("Shef", "Gordon", "Ramsay", "admin@gmail.com", "123", LocalDate.of(1913, 6, 9), "photo", "desc", ROLE.ADMIN);
        userRepository.save(admin1);

        // BOOKS
        List<Book> books;

        Book b1 = new Book("0747532745", "Harry Potter and the Philosopher's Stone", "https://media.harrypotterfanzone.com/sorcerers-stone-us-childrens-edition.jpg", LocalDate.of(2000, 3,25), 223, "desc", 0);
        b1.setGenre(g5);
        bookRepository.save(b1);
        ShelfItem si1 = new ShelfItem(b1);
        shelfItemRepository.save(si1);
        books = auth1.getBooks();
        if (books == null) books = new ArrayList<Book>();
        books.add(b1);
        auth1.setBooks(books);
        authorRepository.save(auth1);

        Book b2 = new Book("0345339738", "The Fellowship of the Ring", "https://churchfieldsjunior.com/wp-content/uploads/2017/10/lord-of-the-rings-tolkien.jpg", LocalDate.of(1954, 8,29), 423, "desc", 0);
        b2.setGenre(g5);
        bookRepository.save(b2);
        ShelfItem si2 = new ShelfItem(b2);
        shelfItemRepository.save(si2);
        books = auth2.getBooks();
        if (books == null) books = new ArrayList<Book>();
        books.add(b2);
        auth2.setBooks(books);
        authorRepository.save(auth2);

        Book b3 = new Book("123", "Harry Potter and the Chamber of Secretes", "https://m.media-amazon.com/images/I/81THMAxo+pL._AC_UF1000,1000_QL80_.jpg", LocalDate.of(1954, 8,29), 423, "desc", 0);
        b3.setGenre(g5);
        bookRepository.save(b3);
        ShelfItem si3 = new ShelfItem(b3);
        shelfItemRepository.save(si3);
        books = auth1.getBooks();
        if (books == null) books = new ArrayList<Book>();
        books.add(b3);
        auth1.setBooks(books);
        authorRepository.save(auth1);

        return true;
    }
}
