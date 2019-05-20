package com.booksrental.bookrental.model;

import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class ApplicationUser {
    @Id
    String id;

    //@Indexed(unique = true, direction = IndexDirection.ASCENDING)
    String firstname;
    String lastname;
    @Indexed(unique = true)
    String username;
    String password;
    String phone;
    private boolean enabled;
    @DBRef
    private Set<Role> roles;

    List<UserReviews> userReviews;
}
