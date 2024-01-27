package com.dictionaryapp.model.dto;

import com.dictionaryapp.validation.register.FieldMatch;
import com.dictionaryapp.validation.register.UniqueEmail;
import com.dictionaryapp.validation.register.UniqueUsername;
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
        message = "{auth.password-confirm}"
)
public class UserRegisterDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "{auth.username.size}")
    @UniqueUsername(message = "{user.unique.username}")
    private String username;

    @NotBlank
    @Email
    @UniqueEmail(message = "{user.email.unique}")
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "{auth.password.size}")
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
