package com.booksrental.bookrental.model;

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
@Document(collection = "user_reviews")
public class UserReviews {
    @Id
    private String id;

    private String description;

    private String authorid;

    private int stars;

    private String created_at;
}
