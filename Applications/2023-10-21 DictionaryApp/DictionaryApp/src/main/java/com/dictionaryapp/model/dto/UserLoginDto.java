package com.dictionaryapp.model.dto;

import com.dictionaryapp.validation.login.EmailAndPasswordMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@EmailAndPasswordMatch(
        uniqueField = "username",
        passwordField = "password",
        message = "{user.login.username-password-match}"
)
public class UserLoginDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "{auth.username.size}")
    private String username;

    @NotBlank
    @Size(min = 3, max = 20, message = "{auth.password.size}")
    private String password;

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "userName='" + username + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}
