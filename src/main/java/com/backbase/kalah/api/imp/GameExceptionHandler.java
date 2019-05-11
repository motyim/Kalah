package com.backbase.kalah.api.imp;

import com.backbase.kalah.dto.ErrorDetails;
import com.backbase.kalah.exception.GameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
//    handle validation exception on primitive params
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> constraintViolationHandler(ConstraintViolationException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bad Request",
                ex.getConstraintViolations().iterator().next().getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(GameException.class)
    protected ResponseEntity<ErrorDetails> hangleGameException(GameException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bad Request",
                ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
