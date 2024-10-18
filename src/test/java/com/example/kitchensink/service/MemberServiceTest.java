package com.example.kitchensink.service;

import com.example.kitchensink.constants.ValidationMessages;
import com.example.kitchensink.entities.Member;
import com.example.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        member = new Member("1", "John Doe", "john.doe@example.com", "+1234567890");
    }

    @Test
    public void testCreateMember_Success() {
        when(memberRepository.save(member)).thenReturn(member);
        Member createdMember = memberService.createMember(member);
        assertEquals(member, createdMember);
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void testCreateMember_InvalidName() {
        member.setName(""); // Nombre vacío
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> memberService.createMember(member)
        );
        assertEquals(ValidationMessages.NAME_REQUIRED, exception.getMessage());
    }

    @Test
    public void testCreateMember_InvalidEmail() {
        member.setEmail("invalid-email"); // Email inválido
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> memberService.createMember(member)
        );
        assertEquals(ValidationMessages.EMAIL_INVALID, exception.getMessage());
    }

    @Test
    public void testCreateMember_InvalidPhoneNumber() {
        member.setPhoneNumber("123"); // Número de teléfono inválido
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> memberService.createMember(member)
        );
        assertEquals(ValidationMessages.PHONE_NUMBER_INVALID, exception.getMessage());
    }

    @Test
    public void testEmailExists() {
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(java.util.Optional.of(member));
        assertTrue(memberService.EmailExist(member));
        verify(memberRepository, times(1)).findByEmail(member.getEmail());
    }

    @Test
    public void testEmailDoesNotExist() {
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(java.util.Optional.empty());
        assertFalse(memberService.EmailExist(member));
        verify(memberRepository, times(1)).findByEmail(member.getEmail());
    }
}
