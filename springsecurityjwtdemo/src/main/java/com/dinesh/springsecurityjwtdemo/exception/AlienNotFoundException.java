package com.dinesh.springsecurityjwtdemo.exception;

public class AlienNotFoundException extends RuntimeException {
    public AlienNotFoundException(String message) {
        super(message);
    }
}
