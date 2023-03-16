package com.likebook.session;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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