package com.booksrental.bookrental.model;

import java.time.ZonedDateTime;
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
@Document(collection = "rented_books")
public class RentedBooks {
    @Id
    String id;
    String bookid;
    String ownerid;
    String renterid;
    String rent_date_start_time;
    String rent_date_end_time;
    String book_return_time;
    String book_return_approval_time;

    //private ZonedDateTime rentDateStartTime;
}
