package com.example.kitchensink.entities;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
@Data
@Builder
public class Member {
    @Id
    private String id; // Agrega un campo id para el identificador único
    private String name;
    private String email;
    private String phoneNumber;
}