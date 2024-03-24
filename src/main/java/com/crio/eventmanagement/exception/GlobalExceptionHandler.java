package com.crio.eventmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crio.eventmanagement.controller.exchange.responses.ErrorBodyResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleFieldsCannotBeNullException(UserAlreadyExistsException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.valueOf(403));
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleFieldsInvalidTokenException(ServerException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(EventDoesNotExistException.class)
    public ResponseEntity<Object> handleEventDoesNotExistException(EventDoesNotExistException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<Object> handleAlreadyRegisteredException(AlreadyRegisteredException e) {
        ErrorBodyResponse errorBodyResponse = new ErrorBodyResponse(e.getMessage());
        return new ResponseEntity<>(errorBodyResponse.getBody(), HttpStatus.valueOf(403));
    }
    
}
