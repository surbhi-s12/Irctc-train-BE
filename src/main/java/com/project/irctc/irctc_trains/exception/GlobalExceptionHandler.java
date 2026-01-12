package com.project.irctc.irctc_trains.exception;

import com.project.irctc.irctc_trains.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ErrorResponse response
                = new ErrorResponse(resourceNotFoundException.getMessage(), "404", false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
