package com.example.kitchensink.services;
import com.example.kitchensink.constants.ValidationMessages;
import com.example.kitchensink.entities.Member;
import com.example.kitchensink.repositorys.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
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
            throw new ValidationException(ValidationMessages.NAME_REQUIRED);
        }
        Pattern pattern = Pattern.compile(ValidationMessages.NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new ValidationException(ValidationMessages.NAME_INVALID);
        }
    }
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException(ValidationMessages.EMAIL_REQUIRED);
        }
        Pattern pattern = Pattern.compile(ValidationMessages.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidationException(ValidationMessages.EMAIL_INVALID);
        }
    }
    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ValidationException(ValidationMessages.PHONE_NUMBER_REQUIRED);
        }
        Pattern pattern = Pattern.compile(ValidationMessages.PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new ValidationException(ValidationMessages.PHONE_NUMBER_INVALID);
        }
    }
    public boolean EmailExist(Member memberDto) {
        return memberRepository.findByEmail(memberDto.getEmail()).isPresent();
    }
}