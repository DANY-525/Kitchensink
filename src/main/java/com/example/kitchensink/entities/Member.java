package com.example.kitchensink.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Document(collection = "members")
@Data
@Builder
@AllArgsConstructor
public class Member {
    @Id
    private String id;
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;
    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "Phone number should be valid.")
    private String phoneNumber;
}
