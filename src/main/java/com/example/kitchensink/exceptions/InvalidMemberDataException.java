package com.example.kitchensink.exceptions;
public class InvalidMemberDataException extends RuntimeException {
    public InvalidMemberDataException(String message) {
        super(message);
    }
}