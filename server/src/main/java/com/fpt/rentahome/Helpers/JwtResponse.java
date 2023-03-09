package com.fpt.rentahome.Helpers;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final String username;
    private final String email;

    public JwtResponse(String jwtToken, String username, String email) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.email = email;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }
}
