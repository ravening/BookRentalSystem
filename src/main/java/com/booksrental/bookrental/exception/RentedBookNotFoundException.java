package com.booksrental.bookrental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentedBookNotFoundException extends RuntimeException {
    public RentedBookNotFoundException(String message) {
        super(message);
    }
}
