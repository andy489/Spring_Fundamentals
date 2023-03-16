package com.battleships.domain.dto;

import com.battleships.domain.validation.register.FieldMatch;
import com.battleships.domain.validation.register.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@FieldMatch(
        firstField = "password",
        secondField = "confirmPassword",
        message = "passwords mismatch"
)
public class UserRegisterDto {

    @NotBlank
    @Size(min = 3, max = 10, message = "length must be between 5 and 20 characters")
    private String userName;

    @NotBlank
    @Size(min = 5, max = 20, message = "length must be between 5 and 20 characters")
    private String fullName;

    @NotBlank
    @Email
    @UniqueEmail(message = "email should be unique")
    private String email;

    @NotBlank
    @Size(min = 3, message = "length must be more than 3 characters long")
    private String password;

    private String confirmPassword;

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                ", confirmPassword='" + (confirmPassword != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }

}

