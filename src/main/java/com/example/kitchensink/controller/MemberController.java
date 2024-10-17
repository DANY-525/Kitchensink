package com.example.kitchensink.controller;


import com.example.kitchensink.exceptions.InvalidMemberDataException;
import com.example.kitchensink.exceptions.MemberAlreadyExistsException;
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
        try {
            memberService.createMember(member);
            return new ResponseEntity<>(member, HttpStatus.CREATED); // Return 201 for creation
        } catch (MemberAlreadyExistsException e) {
            return new ResponseEntity<>("Member already exists.", HttpStatus.CONFLICT);
        } catch (InvalidMemberDataException e) {
            return new ResponseEntity<>("Invalid member data.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
