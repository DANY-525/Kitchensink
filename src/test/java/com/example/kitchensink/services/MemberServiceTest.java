package com.example.kitchensink.services;

import com.example.kitchensink.constants.ValidationMessages;
import com.example.kitchensink.entities.Member;
import com.example.kitchensink.exceptions.EntityNotFoundException;
import com.example.kitchensink.repositorys.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.validation.ValidationException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    public void testFindAll() {
        Member member1 = new Member();
        member1.setName("John Doe");
        member1.setEmail("john@example.com");
        member1.setPhoneNumber("1234567890");
        Member member2 = new Member();
        member2.setName("Jane Doe");
        member2.setEmail("jane@example.com");
        member2.setPhoneNumber("0987654321");
        List<Member> members = Arrays.asList(member1, member2);
        when(memberRepository.findAll()).thenReturn(members);
        List<Member> result = memberService.findAll();
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
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
