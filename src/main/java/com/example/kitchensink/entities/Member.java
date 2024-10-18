package com.example.kitchensink.entities;
import com.example.kitchensink.constants.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Document(collection = "members")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private String id;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    private String name;
    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.EMAIL_INVALID)
    private String email;
    @NotBlank(message = ValidationMessages.PHONE_NUMBER_REQUIRED)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = ValidationMessages.PHONE_NUMBER_INVALID)
    private String phoneNumber;
}
