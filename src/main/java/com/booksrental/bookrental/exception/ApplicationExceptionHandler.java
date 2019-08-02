package com.booksrental.bookrental.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity handleBookNotFoundException(final BookNotFoundException e) {
        ExceptionResponse response = ExceptionResponse.of(e.getMessage(),
                HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleUserNotFoundException(final UserNotFoundException e) {
        ExceptionResponse response = ExceptionResponse.of(e.getMessage(),
                HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RentedBookNotFoundException.class)
    public final ResponseEntity handleRentedBookNotFoundException(final RentedBookNotFoundException e) {
        ExceptionResponse response = ExceptionResponse.of(e.getMessage(),
                HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public final ResponseEntity handleUnverfiedTokenException(final JWTVerificationException e) {
        ExceptionResponse response = ExceptionResponse.of(e.getMessage(),
                HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
