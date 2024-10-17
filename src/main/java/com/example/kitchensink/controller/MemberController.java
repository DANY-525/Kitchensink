package com.example.kitchensink.controller;


import com.example.kitchensink.model.MemberDto;
import com.example.kitchensink.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody MemberDto member){

        memberService.createMember(member);

        return new  ResponseEntity<>(member, HttpStatus.OK);
    }

}
