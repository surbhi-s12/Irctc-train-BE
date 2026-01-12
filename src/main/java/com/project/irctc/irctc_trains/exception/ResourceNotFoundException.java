package com.project.irctc.irctc_trains.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
