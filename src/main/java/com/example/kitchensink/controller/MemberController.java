package com.example.kitchensink.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController

public class MemberController {
    @PostMapping
    public ResponseEntity<Member> addMember (@RequestBody Member member){
        System.out.println(member);
        return null;
    }




}
