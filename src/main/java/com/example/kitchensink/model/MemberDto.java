package com.example.kitchensink.model;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberDto {

    private String name;

    private String email;

    private String phoneNumber;
}
