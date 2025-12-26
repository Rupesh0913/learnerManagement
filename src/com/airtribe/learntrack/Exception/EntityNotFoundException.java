package com.airtribe.learntrack.Exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super();
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
