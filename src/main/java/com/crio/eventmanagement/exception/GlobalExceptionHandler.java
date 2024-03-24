package com.crio.eventmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crio.eventmanagement.controller.exchange.responses.ErrorBodyResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleFieldsCannotBeNullException(UserAlreadyExistsException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleFieldsInvalidTokenException(ServerException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.SERVICE_UNAVAILABLE);
    }
    
}
