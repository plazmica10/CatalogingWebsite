package tim2.cataloging.tim2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.BookDto;
import tim2.cataloging.tim2.model.Book;
import tim2.cataloging.tim2.model.ROLE;
import tim2.cataloging.tim2.model.ShelfItem;
import tim2.cataloging.tim2.model.User;
import tim2.cataloging.tim2.service.BookService;
import tim2.cataloging.tim2.service.ShelfItemService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ShelfItemService shelfItemService;

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
    public Book saveBook(@RequestBody Book book) {
        return this.bookService.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable(name = "id") Long id, @RequestBody Book book) {
        Book existingBook = bookService.findOne(id);
        if (existingBook == null)
            return ResponseEntity.badRequest().body("Book with id: " + id + " does not exist!");
        else {
            existingBook.setTitle(book.getTitle());
            bookService.save(existingBook);
            return ResponseEntity.ok("Successfully updated book with id: " + id);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (loggedUser.getRole() != ROLE.ADMIN)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete books!");

        ShelfItem shelfItem = shelfItemService.findByBookId(id);

        if (bookService.findOne(id) == null)
            return ResponseEntity.badRequest().body("Book with id: " + id + " does not exist!");
        else {
            bookService.deleteById(id);
            return ResponseEntity.ok("Successfully deleted book with id: " + id);
        }
    }
}
