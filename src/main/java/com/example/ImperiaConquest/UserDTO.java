package com.example.ImperiaConquest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 6, message = "Size must be bigger than 6")
    @Size(max = 14, message = "Size must be smaller than 14")
    private String username;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 5, message = "Size must be bigger than 5")
    private String email;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 8, message = "Size must be bigger than 8")
    @Size(max = 16, message = "Size must be smaller than 16")
    private String password;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 8, message = "Size must be bigger than 8")
    @Size(max = 16, message = "Size must be smaller than 16")
    private String repeatPassword;

    @Column(columnDefinition = "VARCHAR DEFAULT 'USER'")
    private String role;

    @Column(columnDefinition = "boolean DEFAULT '1'")
    private boolean enable;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
