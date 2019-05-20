package com.booksrental.bookrental.repository;

import com.booksrental.bookrental.model.ApplicationUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    Optional<List<ApplicationUser>> findByFirstname(String firstName);
    Optional<List<ApplicationUser>> findByLastname(String lastName);
    Optional<ApplicationUser> findByUsernameEqualsIgnoreCase(String username);
}
