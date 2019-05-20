package com.booksrental.bookrental.service;

import com.booksrental.bookrental.exception.UserNotFoundException;
import com.booksrental.bookrental.repository.BooksRepository;
import com.booksrental.bookrental.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booksrental.bookrental.model.*;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private BooksRepository booksRepository;

    UserService(UserRepository userRepository,
                BooksRepository booksRepository) {
        this.userRepository = userRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<ApplicationUser> user = userRepository.findByUsernameEqualsIgnoreCase(s);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Unable to find the user with email " + s);
        }

        return new User(user.get().getUsername(),
                user.get().getPassword(), Collections.emptyList());
    }

    ApplicationUser getUserById(String id) {
        Optional<ApplicationUser> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() ->
                new RuntimeException("Unable to find user by id " + id));
    }

    List<ApplicationUser> getUsersByFirstName(String firstName) {
        Optional<List<ApplicationUser>> userList = userRepository.findByFirstname(firstName);

        return userList.orElse(new ArrayList<>());
    }

    List<ApplicationUser> getUsersByLastName(String lastName) {
        Optional<List<ApplicationUser>> userList = userRepository.findByLastname(lastName);

        return userList.orElse(new ArrayList<>());
    }

    ApplicationUser getUserByUsername(String userName) {
        Optional<ApplicationUser> userList = userRepository.findByUsernameEqualsIgnoreCase(userName);

        return userList.orElse(new ApplicationUser());
    }

    List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    void deleteAllUsers() {
        userRepository.deleteAll();
    }

    ApplicationUser createUser(ApplicationUser applicationUser) {
        userRepository.save(applicationUser);
        return applicationUser;
    }

    ApplicationUser updateUser(ApplicationUser applicationUser) {
        Optional<ApplicationUser> originalUser = userRepository.findById(applicationUser.getId());

        if (!originalUser.isPresent())
            throw new UserNotFoundException("Unable to find the applicationUser : " + applicationUser.getUsername());

        ApplicationUser modifiableApplicationUser = originalUser.get();

        modifiableApplicationUser.setFirstname(applicationUser.getFirstname());
        modifiableApplicationUser.setLastname(applicationUser.getLastname());
        modifiableApplicationUser.setUsername(applicationUser.getUsername());
        modifiableApplicationUser.setPassword(applicationUser.getPassword());
        modifiableApplicationUser.setPhone(applicationUser.getPhone());

        userRepository.save(modifiableApplicationUser);

        return modifiableApplicationUser;
    }

    void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    List<Book> getOwnedBooks(String userId) {
        Optional<List<Book>> optionalBooks =
                booksRepository.findAllByUserId(userId);

        return optionalBooks.orElse(new ArrayList<>());
    }
}
