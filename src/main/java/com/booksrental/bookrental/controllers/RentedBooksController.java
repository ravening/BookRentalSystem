package com.booksrental.bookrental.controllers;

import com.booksrental.bookrental.exception.RentedBookNotFoundException;
import com.booksrental.bookrental.model.RentedBooks;
import com.booksrental.bookrental.service.BookService;
import com.booksrental.bookrental.service.RentedBooksService;
import com.booksrental.bookrental.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/rentedbooks")
public class RentedBooksController {
    private RentedBooksService rentedBooksService;
    private UserService userService;
    private BookService booksService;

    RentedBooksController(RentedBooksService rentedBooksService,
                          UserService userService,
                          BookService booksService) {
        this.rentedBooksService = rentedBooksService;
        this.userService = userService;
        this.booksService = booksService;
    }

    @GetMapping("/listall")
    public ResponseEntity<List<RentedBooks>> getAllRentedBooks() {
        log.info("Fetching all rented books");
        List<RentedBooks> rentedBooks = rentedBooksService.getAllRentedBooks();
        if (rentedBooks == null || rentedBooks.size() == 0)
            log.info("Didnt find any rented books");

        return ResponseEntity.ok().body(rentedBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRentedBookById(@PathVariable("id") String id) {
        log.info("Fetching rented book details by id : {}", id);
        Optional<RentedBooks> rentedBooks = rentedBooksService.getRentedBookById(id);
        RentedBooks books = rentedBooks.orElseThrow(() ->
                new RentedBookNotFoundException("Unable to find to rented book details for id : {}" + id));

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/renter/{id}")
    public ResponseEntity<List<RentedBooks>> getRentedBooksByRenterId(@PathVariable("id") String id) {
        log.info("Getting all the rented books for the renter id : {}", id);
        Optional<List<RentedBooks>> rentedBooksList = rentedBooksService
                .getRentedBooksByRenterId(id);

        List<RentedBooks> rentedBooks = rentedBooksList.orElseThrow(
                () -> new RentedBookNotFoundException("Unable to find rented books for renter id : " + id));
        return ResponseEntity.ok().body(rentedBooks);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<RentedBooks>> getRentedBooksByOwnerId(@PathVariable("id") String id) {
        log.info("Getting all the rented books for the owner id : {}", id);
        Optional<List<RentedBooks>> rentedBooksList = rentedBooksService
                .getRentedBooksByOwnerId(id);

        List<RentedBooks> rentedBooks = rentedBooksList.orElseThrow(
                () -> new RentedBookNotFoundException("Unable to find rented books for owner id : " + id));
        return ResponseEntity.ok().body(rentedBooks);
    }

    @PostMapping("/create")
    public ResponseEntity createdRentedBookEntry(@Valid @RequestBody RentedBooks rentedBooks) {
        log.info("Creating a new rented book entry : {}", rentedBooks);
        rentedBooksService.saveRentedBook(rentedBooks);

        return ResponseEntity.status(HttpStatus.CREATED).body(rentedBooks);
    }

    @PutMapping("/update")
    public ResponseEntity updateRentedBookEntry(@Valid @RequestBody RentedBooks rentedBooks) {
        log.info("Updating the rented book entry with new value : {}", rentedBooks);
        rentedBooksService.updateRentedBook(rentedBooks);

        return ResponseEntity.ok().body(rentedBooks);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRentedBookById(@PathVariable("id") String id) {
        log.info("Deleting rented book entry with id : {}", id);
        rentedBooksService.deleteRentedBookById(id);

        return ResponseEntity.ok().body("Entry deleted successfully");
    }

    @DeleteMapping("/delete/owner/{id}")
    public ResponseEntity deleteRentedBooksByOwnerId(@PathVariable("id") String id) {
        log.info("Deleting rented books rented by the owner id : {}", id);
        rentedBooksService.deleteRentedBooksByOwnerId(id);

        return ResponseEntity.ok().body("Rented books deleted successfully");
    }

    @DeleteMapping("/delete/renter/{id}")
    public ResponseEntity deleteRentedBooksByRenterId(@PathVariable("id") String id) {
        log.info("Deleting rented books rented for the renter id : {}", id);
        rentedBooksService.deleteRentedBooksByRenterId(id);

        return ResponseEntity.ok().body("Rented books deleted successfully");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAllRentedBooks() {
        log.info("Deleting all rented books");
        rentedBooksService.deleteAllRentedBooks();

        return ResponseEntity.ok().body("All rented books deleted successfully");
    }
}
