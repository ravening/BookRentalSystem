package com.booksrental.bookrental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String s) {
        super(s);
    }

    public BookNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BookNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected BookNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
