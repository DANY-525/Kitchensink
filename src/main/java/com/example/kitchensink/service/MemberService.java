package com.example.kitchensink.service;

import com.example.kitchensink.entities.Member;
import com.example.kitchensink.model.MemberDto;
import com.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Expresiones regulares para validación
    private static final String NAME_PATTERN = "^[a-zA-Z\\s]+$"; // Solo letras y espacios
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Formato de email
    private static final String PHONE_PATTERN = "^\\+?[0-9. ()-]{7,}$"; // Formato de número de teléfono

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member createMember(Member memberDto) {
        validateMember(memberDto);
        return memberRepository.save(memberDto);
    }


    private void validateMember(Member member) {
        validateName(member.getName());
        validateEmail(member.getEmail());
        validatePhoneNumber(member.getPhoneNumber());
    }


    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name is required.");
        }
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new ValidationException("Name can only contain letters and spaces.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email is required.");
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidationException("Email should be valid.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ValidationException("Phone number is required.");
        }
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new ValidationException("Phone number should be valid.");
        }
    }



    public boolean EmailExist(Member memberDto){
        return  memberRepository.findByEmail(memberDto.getEmail()).isPresent();
    }
}
