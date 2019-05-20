package com.booksrental.bookrental.controller;

import com.booksrental.bookrental.model.ApplicationUser;
//import com.booksrental.bookrental.service.CustomUserDetailsService;
import com.booksrental.bookrental.repository.UserRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository repository,
                          BCryptPasswordEncoder encoder) {
        this.userRepository = repository;
        this.bCryptPasswordEncoder = encoder;
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        userRepository.save(applicationUser);
    }

//    private CustomUserDetailsService customUserDetailsService;
//
//    UserController(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }

//    @PostMapping("/signup")
//    public ResponseEntity createNewUser(@Valid @RequestBody ApplicationUser user) {
//        Optional<ApplicationUser> optionalUser = customUserDetailsService
//                .findUserByEmail(user.getEmail());
//        if (optionalUser.isPresent())
//            throw new RuntimeException("ApplicationUser with email already exists");
//
//        customUserDetailsService.saveUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
}
