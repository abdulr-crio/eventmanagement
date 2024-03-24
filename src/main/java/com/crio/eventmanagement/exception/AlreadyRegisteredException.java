package com.crio.eventmanagement.exception;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException(String message){
        super(message);
    }
}
