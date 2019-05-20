package com.booksrental.bookrental.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private HttpStatus httpStatus;

    public static ExceptionResponse of(String message,
                                       HttpStatus httpStatus) {
        return new ExceptionResponse(message,httpStatus);
    }
}
