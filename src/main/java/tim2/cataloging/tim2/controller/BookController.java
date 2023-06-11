package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.BookDto;
import tim2.cataloging.tim2.model.*;
import tim2.cataloging.tim2.model.*;
import tim2.cataloging.tim2.service.AuthorService;
import tim2.cataloging.tim2.service.BookService;
import tim2.cataloging.tim2.service.ShelfItemService;
import tim2.cataloging.tim2.service.ShelfService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ShelfItemService shelfItemService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private AuthorService authorService;

    // READ ALL
    @GetMapping("")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> bookList = bookService.findAll();

        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookList) {
            BookDto dto = new BookDto(book);
            bookDtoList.add(dto);
        }

        return ResponseEntity.ok(bookDtoList);
    }

    // READ ONE
    @GetMapping("/{id}")
    public Book getBook(@PathVariable(name = "id") Long id) {
        return bookService.findOne(id);
    }

    // CREATE
    @PostMapping("")
    public ResponseEntity<Book> saveBook(@RequestBody Book book, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        if (loggedUser.getRole() == ROLE.READER)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        Book book1 = bookService.findByTitle(book.getTitle());
        if (book1 != null)
            return ResponseEntity.badRequest().body(null);
        book1 = bookService.findByIsbn(book.getISBN());
        if (book1 != null)
            return ResponseEntity.badRequest().body(null);

        bookService.save(book);

        if (loggedUser.getRole() == ROLE.AUTHOR) {
            Author author = authorService.findOne(loggedUser.getId());
            author.getBooks().add(book);
            authorService.save(author);
        }

        ShelfItem shelfItem = new ShelfItem();
        shelfItem.setBook(book);
        shelfItemService.save(shelfItem);

        return ResponseEntity.ok(book);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable(name = "id") Long id, @RequestBody Book book, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update books!");
        if (loggedUser.getRole() == ROLE.READER)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update books!");
        if (loggedUser.getRole() == ROLE.AUTHOR) {
            Author author = authorService.findOne(loggedUser.getId());
            if (author == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update books!");
            if (author.getBooks().stream().noneMatch(b -> b.getId().equals(id)))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update this book!");
        }

        Book existingBook = bookService.findOne(id);
        if (existingBook == null)
            return ResponseEntity.badRequest().body("Book with id: " + id + " does not exist!");

        if (book.getTitle() != null)
            existingBook.setTitle(book.getTitle());
        if (book.getDescription() != null)
            existingBook.setDescription(book.getDescription());
        if (book.getISBN() != null)
            existingBook.setISBN(book.getISBN());
        if (book.getGenre() != null)
            existingBook.setGenre(book.getGenre());
        if (book.getDate() != null)
            existingBook.setDate(book.getDate());
        if (book.getPages() != 0)
            existingBook.setPages(book.getPages());
        if (book.getPhoto() != null)
            existingBook.setPhoto(book.getPhoto());

        bookService.save(existingBook);
        return ResponseEntity.ok("Successfully updated book with id: " + id);

    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete books!");
        if (loggedUser.getRole() == ROLE.READER)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete books!");
        if (loggedUser.getRole() == ROLE.AUTHOR) {
            Author author = authorService.findOne(loggedUser.getId());
            if (author == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete books!");
            if (author.getBooks().stream().noneMatch(b -> b.getId().equals(id)))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete this book!");
        }

        ShelfItem shelfItem = shelfItemService.findByBookId(id);
        if (!shelfItem.getReviews().isEmpty())
            return ResponseEntity.badRequest().body("Book with id: " + id + " has reviews and cannot be deleted!");

        List<Shelf> shelves = shelfService.findAll();
        for (Shelf s : shelves) {
//            s.getShelfItems().remove(shelfItem);
            s.getShelfItems().removeIf(shelfItem1 -> shelfItem1.getId().equals(shelfItem.getId()));
            shelfService.save(s);
        }
//        shelfService.saveAll(shelves);

        shelfItemService.deleteById(shelfItem.getId());

        // TODO delete book from AUTHORS_BOOKS table - check if done
        List<Author> authors = authorService.findAll();
        for (Author a : authors) {
            a.getBooks().remove(bookService.findOne(id));
        }

        if (bookService.findOne(id) == null)
            return ResponseEntity.badRequest().body("Book with id: " + id + " does not exist!");
        else {
            bookService.deleteById(id);
            return ResponseEntity.ok("Successfully deleted book with id: " + id);
        }
    }
    //SEARCH
    @GetMapping("/search/{text}")
    public ResponseEntity<List<BookDto>> searchBooks(@PathVariable(name = "text") String text) {
        List<Book> bookList = bookService.findAll();
        List<Book> result = new ArrayList<>();
        for(Book b:bookList){
            if(b.getTitle().toLowerCase().contains(text.toLowerCase()) || b.getGenre().getName().toLowerCase().contains(text.toLowerCase()) || b.getISBN().toLowerCase().contains(text.toLowerCase()))
                result.add(b);
        }
        if(result.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book b:result){
            BookDto dto = new BookDto(b);
            bookDtoList.add(dto);
        }
        return ResponseEntity.ok(bookDtoList);
    }

}
