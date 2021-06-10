package com.meli.homeAppraisal.domain.handler;

import com.meli.homeAppraisal.domain.error.StandardError;
import com.meli.homeAppraisal.domain.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <?> handleResourceNotFoundException(ResourceNotFoundException exception){
        var error = StandardError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("District not found")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity <>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validateField(MethodArgumentNotValidException e){
        var error = StandardError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("there is something wrong with your request")
                .detail(e.getAllErrors().get(0).getDefaultMessage())
                .developerMessage(e.getClass().getName())
                .build();
        return new ResponseEntity <>(error, HttpStatus.BAD_REQUEST);
    }
}
