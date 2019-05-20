package com.booksrental.bookrental.repository;

import com.booksrental.bookrental.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}
