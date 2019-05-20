package com.booksrental.bookrental.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "owned_books")
public class OwnedBooks {
    @Id
    String id;
    String bookId;
    String ownerId;
}
