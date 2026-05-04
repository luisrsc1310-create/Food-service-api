package org.example.foodprojectjpa.API.ExceptionsHandlers;

public class AlreadyExists extends RuntimeException{

    public AlreadyExists(String message) {
        super(message);
    }

}
