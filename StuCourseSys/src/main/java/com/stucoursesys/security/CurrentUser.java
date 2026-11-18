package com.stucoursesys.security;

import java.util.Set;

public class CurrentUser {

    private final Long id;

    private final String studentNo;

    private final Set<String> roles;

    public CurrentUser(Long id, String studentNo, Set<String> roles) {
        this.id = id;
        this.studentNo = studentNo;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
