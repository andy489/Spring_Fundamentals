package com.battleships.domain.dto;

import com.battleships.domain.validation.login.UsernameAndPasswordMatch;
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
@UsernameAndPasswordMatch(
        uniqueField = "username",
        passwordField = "password",
        message = "invalid user and password combination"
)
public class UserLoginDto {

    @NotBlank
    @Size(min = 4, max = 20, message = "length must be between 4 and 20 characters")
    private String username;

    @NotBlank
    @Size(min = 3, message = "length must be more than 3 characters long")
    private String password;

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "userName='" + username + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}



