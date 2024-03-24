package com.crio.eventmanagement.exception;

public class EventDoesNotExistException extends RuntimeException {
    
    public EventDoesNotExistException(String message){
        super(message);
    }
}
