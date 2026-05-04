package org.example.foodprojectjpa.API.ExceptionsHandlers;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
