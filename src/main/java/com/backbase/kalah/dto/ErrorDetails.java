package com.backbase.kalah.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorDetails {
    private final LocalDateTime timestamp;
    private final String message;
    private final HttpStatus status;

    private ErrorDetails(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorDetails of(final String message,
                                       HttpStatus status){
        return new ErrorDetails(message, status);
    }

}
