package com.stucoursesys.dto;

public class LoginResult {

    private final String token;

    private final String tokenType;

    private final long expiresIn;

    private final UserVO user;

    public LoginResult(String token, String tokenType, long expiresIn, UserVO user) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public UserVO getUser() {
        return user;
    }
}
