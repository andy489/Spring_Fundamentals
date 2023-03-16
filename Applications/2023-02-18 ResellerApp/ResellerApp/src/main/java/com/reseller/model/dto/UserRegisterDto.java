package com.reseller.model.dto;

import com.reseller.model.validation.register.FieldMatch;
import com.reseller.model.validation.register.UniqueEmail;
import com.reseller.model.validation.register.UniqueUsername;
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
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    @UniqueUsername(message = "username must be unique")
    private String username;

    @NotBlank
    @Email
    @UniqueEmail(message = "email must be unique")
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    private String password;

    private String confirmPassword;

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                ", confirmPassword='" + (confirmPassword != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}
