package com.spotifly.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
public class CurrentUser {

    private Long id;

    private String username;

    private String email;

    private Boolean loggedIn;

    public CurrentUser() {
        this.loggedIn = false;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public void clear() {
        id = null;
        username = null;
        email = null;
        loggedIn = false;
    }

}