package com.example.kitchensink.controllers;
import com.example.kitchensink.constants.ValidationMessages;
import com.example.kitchensink.entities.Member;
import com.example.kitchensink.exceptions.MemberAlreadyExistsException;
import com.example.kitchensink.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    public ResponseEntity<?> addMember(@Valid  @RequestBody Member member){
        try {
            if(memberService.EmailExist(member)){
                throw new MemberAlreadyExistsException(ValidationMessages.MEMBER_ALREADY_EXIST + member.getEmail());
            }
            else{
                memberService.createMember(member);
                return new ResponseEntity<>(member, HttpStatus.CREATED); // Return 201 for creation
            }
        }catch (Exception e) {
            String errorMessage = ValidationMessages.UNEXPECTED_ERROR + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
