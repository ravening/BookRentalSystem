package com.booksrental.bookrental.model.googlebook;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "google_books")
public class GoogleBook {
    @Id
    String id;
    int totalItems;
    List<GoogleBookItem> items;
}
