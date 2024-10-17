package com.example.kitchensink.exceptions;

public class MethodArgumentNotValidException  extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
