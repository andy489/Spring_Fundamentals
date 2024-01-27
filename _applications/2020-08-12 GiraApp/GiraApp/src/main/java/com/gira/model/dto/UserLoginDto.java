package com.gira.model.dto;

import com.gira.model.validation.login.EmailAndPasswordMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EmailAndPasswordMatch(
        uniqueField = "email",
        passwordField = "password",
        message = "invalid email and password combination"
)
public class UserLoginDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    private String password;

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "userName='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }

}

