package com.example.kitchensink.exceptions;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.kitchensink.exceptions.GlobalExceptionHandler;
import com.example.kitchensink.exceptions.InvalidMemberDataException;
import com.example.kitchensink.exceptions.MemberAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleMemberAlreadyExists() {
        // Arrange
        MemberAlreadyExistsException exception = new MemberAlreadyExistsException("Member already exists");

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleMemberAlreadyExists(exception);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Member already exists", response.getBody());
    }

    @Test
    public void testHandleInvalidMemberData() {
        // Arrange
        InvalidMemberDataException exception = new InvalidMemberDataException("Invalid member data");

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleInvalidMemberData(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid member data", response.getBody());
    }

    @Test
    public void testHandleGlobalException() {
        // Arrange
        Exception exception = new Exception("Unexpected error");

        // Act
        ResponseEntity<String> response = globalExceptionHandler.handleGlobalException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred: Unexpected error", response.getBody());
    }


}
