package com.example.kitchensink.exceptions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    }
    @Test
    public void testEntityNotFoundExceptionMessage() {
        String errorMessage = "Member with ID 1 not found";
        EntityNotFoundException exception = new EntityNotFoundException(errorMessage);

        // Assert that the message is as expected
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testMethodArgumentNotValidExceptionMessage() {
        String errorMessage = "Invalid method argument";
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(errorMessage);

        // Assert that the message is as expected
        assertEquals(errorMessage, exception.getMessage());
    }


}
