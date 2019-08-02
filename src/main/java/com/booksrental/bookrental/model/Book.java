package com.booksrental.bookrental.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "books")
public class Book {
    @Id
    String id;

    String name;
    String author;
    String userId;
    String isbn;
    String genre;
    String year;
    String url;
    int bookRating;
    int count;
    boolean isRented;
    @DBRef
    List<BookReviews> bookReviews;
}
