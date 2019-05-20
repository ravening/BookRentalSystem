package com.booksrental.bookrental.repository;

import com.booksrental.bookrental.model.RentedBooks;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RentedBooksRepository extends MongoRepository<RentedBooks, String> {
    Optional<List<RentedBooks>> findAllByRenterid(String renterId);
    Optional<List<RentedBooks>> findAllByOwnerid(String ownerId);
    void deleteAllByRenterid(String renterId);
    void deleteAllByOwnerid(String ownerId);
}
