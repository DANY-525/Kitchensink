package com.example.kitchensink.entities;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private final Validator validator;

    public MemberTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidMember() {
        Member member = new Member("1", "John Doe", "john.doe@example.com", "+1234567890");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertTrue(violations.isEmpty(), "Should be valid");
    }

    @Test
    public void testInvalidName() {
        Member member = new Member("1", "", "john.doe@example.com", "+1234567890");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertEquals(1, violations.size());
        assertEquals("Name is required.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidEmail() {
        Member member = new Member("1", "John Doe", "invalid-email", "+1234567890");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertEquals(1, violations.size());
        assertEquals("Email should be valid.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPhoneNumber() {
        Member member = new Member("1", "John Doe", "john.doe@example.com", "123");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertEquals(1, violations.size());
        assertEquals("Phone number should be valid.", violations.iterator().next().getMessage());
    }

    @Test
    public void testAllInvalidFields() {
        Member member = new Member("1", "", "invalid-email", "123");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertEquals(3, violations.size());
    }
}
