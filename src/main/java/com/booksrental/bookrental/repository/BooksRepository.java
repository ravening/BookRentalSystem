package com.booksrental.bookrental.repository;

import com.booksrental.bookrental.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BooksRepository extends MongoRepository<Book, String> {
    Optional<List<Book>> findByAuthorContainsIgnoreCase(String authorName);
    Optional<List<Book>> findByUserId(String userId);
    Optional<List<Book>> findAllByUserId(String userId);
    Optional<List<Book>> findByNameContainsIgnoreCase(String name);
    @Override
    Iterable<Book> findAllById(Iterable<String> strings);

    void deleteBookByUserId(String id);

    //Optional<List<Book>> findCustom
}

