package com.kovalenko.diploma.utils.jwtUtils;

public class AuthenticationRequest {
    private String password;
    private String login;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
