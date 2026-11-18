package com.stucoursesys.dto;

import java.util.List;

public class UserVO {

    private final Long id;

    private final String username;

    private final String displayName;

    private final List<String> roles;

    public UserVO(Long id, String username, String displayName, List<String> roles) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getRoles() {
        return roles;
    }
}
