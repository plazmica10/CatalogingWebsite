package tim2.cataloging.tim2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim2.cataloging.tim2.dto.BookDto;
import tim2.cataloging.tim2.model.Book;
import tim2.cataloging.tim2.service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

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
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long id) {
        if (bookService.findOne(id) == null)
            return ResponseEntity.badRequest().body("Book with id: " + id + " does not exist!");
        else {
            bookService.deleteById(id);
            return ResponseEntity.ok("Successfully deleted book with id: " + id);
        }
    }
}
