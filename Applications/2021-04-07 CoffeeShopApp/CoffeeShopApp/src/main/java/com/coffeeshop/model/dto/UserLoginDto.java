package com.coffeeshop.model.dto;

import com.coffeeshop.model.validation.login.UsernameAndPasswordMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@UsernameAndPasswordMatch(
        uniqueField = "username",
        passwordField = "password",
        message = "invalid email and password combination"
)
public class UserLoginDto {

    @NotBlank
    @Size(min = 5, max = 20, message = "length must be between 5 and 20 characters")
    private String username;

    @NotBlank
    @Size(min = 3, message = "length must be at least 3 characters")
    private String password;

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "userName='" + username + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}

