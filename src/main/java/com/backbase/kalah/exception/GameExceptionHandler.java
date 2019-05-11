package com.backbase.kalah.exception;

import com.backbase.kalah.dto.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
//    handle validation exception on primitive params
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> constraintViolationHandler(ConstraintViolationException ex) {
        ErrorDetails errorDetails = ErrorDetails.of(ex.getConstraintViolations().iterator().next().getMessage()
                , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(GameException.class)
    protected ResponseEntity<ErrorDetails> hangleGameException(GameException ex){
        ErrorDetails errorDetails = ErrorDetails.of(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
