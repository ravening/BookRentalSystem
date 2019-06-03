package com.booksrental.bookrental.controllers;

import com.booksrental.bookrental.model.Book;
import com.booksrental.bookrental.service.BookService;
import com.booksrental.bookrental.service.BookService;
import io.micrometer.core.annotation.Timed;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private BookService booksService;

    BooksController(BookService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/listall")
    public ResponseEntity getAllBooks() {
        log.info("Fetching all the books");
        return ResponseEntity.ok().body(booksService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookById(@PathVariable("id") String id) {
        log.info("Fetchging the book by id {}", id);
        Book book = booksService.getBookById(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/bookname/{name}")
    public ResponseEntity<List<Book>> getBookByName(@PathVariable("name") String name) {
        log.info("Fetching book by name : {}", name);
        List<Book> bookList = booksService.getBooksByName(name);
        if (bookList == null || bookList.size() == 0)
            log.error("Didnt find any books with name : {}", name);

        return ResponseEntity.ok().body(bookList);
    }

    @GetMapping("/authorname/{name}")
    public ResponseEntity<List<Book>> getBooksByAuthorName(@PathVariable("name") String name) {
        log.info("Fetching books by author name : {}", name);
        List<Book> bookList = booksService.getBooksByAuthorName(name);
        if (bookList == null || bookList.size() == 0)
            log.error("Didnt find any books with author name : {}", name);

        return ResponseEntity.ok().body(bookList);
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<List<Book>> getBooksByUserId(@PathVariable("id") String id) {
        log.info("Fetching the books for the user id : {}", id);
        List<Book> bookList = booksService.getBooksByUserId(id);
        if (bookList == null || bookList.size() == 0)
            log.error("Didn't find any books for the user id : {}", id);

        return ResponseEntity.ok().body(bookList);
    }

    @PostMapping("/create")
    public ResponseEntity createBook(@Valid @RequestBody Book book) {
        log.info("Creating a new book : {}", book);
        booksService.createBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PostMapping("/createbooks")
    public ResponseEntity<List<Book>> createBooks(@Valid @RequestBody List<Book> books) {
        log.info("Creating a list of books");
        booksService.createBooks(books);

        return ResponseEntity.status(HttpStatus.CREATED).body(books);
    }

//    @PutMapping("/update")
//    public ResponseEntity updateBook(@Valid @RequestBody Book book) {
//        log.info("Updating the book with new values : {}", book);
//        booksService.updateBook(book);
//
//        return ResponseEntity.ok().body(book);
//    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") String id, @Valid @RequestBody Book book) {
        log.info("Updating the book with new values {}", book);
        book.setId(id);
        return new ResponseEntity<>(booksService.updateBook(book),HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity deleteAllBooks() {
        log.info("===Deleting all books===");
        booksService.deleteAllBooks();

        return ResponseEntity.ok().body("All books deleted successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") String id) {
        log.info("Deleting the book by id : {}", id);
        booksService.deleteBookById(id);

        return ResponseEntity.ok().body("Book deleted successfully");
    }

    @DeleteMapping("/delete/userid/{id}")
    public ResponseEntity deleteBooksByUserId(@PathVariable("id") String id) {
        log.info("Deleting the books of user : {}", id);
        booksService.deleteBooksByUserId(id);

        return ResponseEntity.ok().body("Books deleted successfully");
    }
}
