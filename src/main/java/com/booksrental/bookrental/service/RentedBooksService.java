package com.booksrental.bookrental.service;

import com.booksrental.bookrental.exception.RentedBookNotFoundException;
import com.booksrental.bookrental.exception.UserNotFoundException;
import com.booksrental.bookrental.model.RentedBooks;
import com.booksrental.bookrental.model.ApplicationUser;
import com.booksrental.bookrental.repository.BooksRepository;
import com.booksrental.bookrental.repository.RentedBooksRepository;
import com.booksrental.bookrental.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RentedBooksService {
    private RentedBooksRepository rentedBooksRepository;
    private BooksRepository booksRepository;
    private UserRepository userRepository;

    RentedBooksService(RentedBooksRepository rentedBooksRepository,
                       BooksRepository booksRepository,
                       UserRepository userRepository) {
        this.rentedBooksRepository = rentedBooksRepository;
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }

    public Optional<List<RentedBooks>> getRentedBooksByRenterId(String renterId) {
        Optional<List<RentedBooks>> rentedBooks = rentedBooksRepository
                .findAllByRenterid(renterId);

        return rentedBooks;
    }

    public Optional<List<RentedBooks>> getRentedBooksByOwnerId(String ownerId) {
        Optional<List<RentedBooks>> rentedBooks = rentedBooksRepository
                .findAllByOwnerid(ownerId);

        return rentedBooks;
    }

    public List<RentedBooks> getAllRentedBooks() {
        return rentedBooksRepository.findAll();
    }

    public Optional<RentedBooks> getRentedBookById(String id) {
        return rentedBooksRepository.findById(id);
    }

    public RentedBooks saveRentedBook(RentedBooks rentedBooks) {
        rentedBooksRepository.save(rentedBooks);
        return rentedBooks;
    }

    public RentedBooks updateRentedBook(RentedBooks rentedBooks) {
        Optional<RentedBooks> rentedBooks1 = rentedBooksRepository
                .findById(rentedBooks.getId());
        RentedBooks rentedBooks2 = rentedBooks1.orElseThrow(()
                -> new RentedBookNotFoundException("Unable to find rented book entry by id : " +
                rentedBooks.getId())
        );

        rentedBooks2.setOwnerid(rentedBooks.getOwnerid());
        rentedBooks2.setRenterid(rentedBooks.getRenterid());
        rentedBooks2.setRent_date_start_time(rentedBooks.getRent_date_start_time());
        rentedBooks2.setRent_date_end_time(rentedBooks.getRent_date_end_time());
        rentedBooks2.setBook_return_time(rentedBooks.getBook_return_time());
        rentedBooks2.setBook_return_approval_time(rentedBooks.getBook_return_approval_time());

        rentedBooksRepository.save(rentedBooks2);
        return rentedBooks2;
    }

    public void deleteRentedBookById(String id) {
        Optional<RentedBooks> rentedBooks = rentedBooksRepository
                .findById(id);
        if (!rentedBooks.isPresent())
            throw new RentedBookNotFoundException("Unable to find the rented book entry by id : " + id);
        rentedBooksRepository.deleteById(id);
    }

    public void deleteRentedBooksByOwnerId(String id) {
        Optional<ApplicationUser> optionalUser = userRepository
                .findById(id);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("Unable to find the user by id " + id);

        rentedBooksRepository.deleteAllByOwnerid(id);
    }

    public void deleteRentedBooksByRenterId(String id) {
        Optional<ApplicationUser> optionalUser = userRepository
                .findById(id);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("Unable to find the user by id " + id);

        rentedBooksRepository.deleteAllByRenterid(id);
    }

    public void deleteAllRentedBooks() {
        rentedBooksRepository.deleteAll();
    }
}
