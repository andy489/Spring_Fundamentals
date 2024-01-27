package com.coffeeshop.model.dto;

import com.coffeeshop.model.validation.register.FieldMatch;
import com.coffeeshop.model.validation.register.UniqueEmail;
import com.coffeeshop.model.validation.register.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@FieldMatch(
        firstField = "password",
        secondField = "confirmPassword",
        message = "passwords mismatch"
)
public class UserRegisterDto {
    @NotBlank
    @Size(min = 5, max = 20, message = "length must be between 5 and 20 characters")
    @UniqueUsername(message = "username must be unique")
    private String username;

    private String firstName;

    @NotBlank
    @Size(min = 5, max = 20, message = "length must be between 5 and 20 characters")
    private String lastName;

    @NotBlank
    @Email
    @UniqueEmail(message = "email must be unique")
    private String email;

    @NotBlank
    @Size(min = 3, message = "length must be at least 3 characters")
    private String password;

    private String confirmPassword;

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "userName='" + username + '\'' +
                "firstName='" + (firstName != null ? firstName : "[NOT PROVIDED]") + '\'' +
                "lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                ", confirmPassword='" + (confirmPassword != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}
