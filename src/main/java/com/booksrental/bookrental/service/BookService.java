package com.booksrental.bookrental.service;

import com.booksrental.bookrental.exception.BookNotFoundException;
import com.booksrental.bookrental.model.Book;
import com.booksrental.bookrental.repository.BooksRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BooksRepository booksRepository;

    BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Book getBookById(String id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        return optionalBook.orElseThrow(() ->
                new BookNotFoundException("Unable to find the book by id " + id));
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public List<Book> getBooksByName(String name) {
        Optional<List<Book>> bookList = booksRepository.findByNameContainsIgnoreCase(name);

        return bookList.orElse(new ArrayList<>());
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        Optional<List<Book>> bookList = booksRepository.findByAuthorContainsIgnoreCase(authorName);
        return bookList.orElse(new ArrayList<>());
    }

    public List<Book> getBooksByUserId(String userId) {
        Optional<List<Book>> bookList = booksRepository.findByUserId(userId);

        return bookList.orElse(new ArrayList<>());
    }

    public Book createBook(Book book) {
        booksRepository.save(book);
        return book;
    }

    public List<Book> createBooks(List<Book> books) {
        booksRepository.saveAll(books);
        return books;
    }

    public Book updateBook(Book book) {
        Optional<Book> optionalBook = booksRepository.findById(book.getId());

        if (!optionalBook.isPresent())
            throw new BookNotFoundException("Unable to find the book by id : " + book.getId());

        Book modifiableBook = optionalBook.get();
        modifiableBook.setName(book.getName());
        modifiableBook.setAuthor(book.getAuthor());
        modifiableBook.setUserId(book.getUserId());
        modifiableBook.setRented(book.isRented());

        booksRepository.save(modifiableBook);
        return modifiableBook;
    }

    public void deleteAllBooks() {
        booksRepository.deleteAll();
    }

    public void deleteBookById(String id) {
        booksRepository.deleteById(id);
    }

    public void deleteBooksByUserId(String id) {
        booksRepository.deleteBookByUserId(id);
    }
}
