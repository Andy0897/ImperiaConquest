package com.example.ImperiaConquest.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 6, message = "Size must be greater than 6")
    @Size(max = 14, message = "Size must be smaller than 14")
    private String username;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 5, message = "Size must be greater than 5")
    private String email;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 8, message = "Size must be greater than 8")
    @Size(max = 16, message = "Size must be smaller than 16")
    private String password;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 8, message = "Size must be greater than 8")
    @Size(max = 16, message = "Size must be smaller than 16")
    private String repeatPassword;

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


}