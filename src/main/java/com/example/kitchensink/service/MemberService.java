package com.example.kitchensink.service;

import com.example.kitchensink.entities.Member;
import com.example.kitchensink.model.MemberDto;
import com.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member createMember(@RequestBody MemberDto memberDto) {

        Member memberA =  Member.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getEmail())
                .build();

        return memberRepository.save(memberA);
    }






}
