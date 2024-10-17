package com.example.kitchensink.controller;

import com.example.kitchensink.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RequestMapping
public class MemberController {

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody Member member){
          return new  ResponseEntity<>(member, HttpStatus.OK);
    }

}
