package com.example.kitchensink.constants;

public class ValidationMessages {
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred:";
    public static final String MEMBER_ALREADY_EXIST = "Member already exists with this email::";
    public static final String NAME_REQUIRED = "Name is required.";
    public static final String NAME_INVALID = "Name can only contain letters and spaces.";
    public static final String EMAIL_REQUIRED = "Email is required.";
    public static final String EMAIL_INVALID = "Email should be valid.";
    public static final String PHONE_NUMBER_REQUIRED = "Phone number is required.";
    public static final String PHONE_NUMBER_INVALID = "Phone number should be valid.";
    public static final String NAME_PATTERN = "^[a-zA-Z\\s]+$"; // Solo letras y espacios
    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Formato de email
    public static final String PHONE_PATTERN = "^\\+?[0-9. ()-]{7,}$"; // Formato de número de teléfono


}