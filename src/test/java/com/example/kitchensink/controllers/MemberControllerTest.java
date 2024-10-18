package com.example.kitchensink.controllers;

import com.example.kitchensink.constants.ValidationMessages;
import com.example.kitchensink.entities.Member;
import com.example.kitchensink.services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        member = Member.builder()
                .id("1") // Puedes establecer un ID si lo necesitas
                .name("John Doe")
                .email("john.doe@example.com")
                .phoneNumber("+1234567890")
                .build();
    }
    @Test
    public void testAddMember_Success() {
        when(memberService.EmailExist(member)).thenReturn(false);
        when(memberService.createMember(member)).thenReturn(member);
        ResponseEntity<?> response = memberController.addMember(member);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(member, response.getBody());
        verify(memberService).createMember(member);
    }
    @Test
    public void testAddMember_UnexpectedError() {
        when(memberService.EmailExist(member)).thenThrow(new RuntimeException("Database error"));
        ResponseEntity<?> response = memberController.addMember(member);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ValidationMessages.UNEXPECTED_ERROR + "Database error", response.getBody());
    }
}
