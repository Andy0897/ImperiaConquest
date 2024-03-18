package com.example.ImperiaConquest;

public class UserMapping {
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(user.getPassword());
        return user;
    }
}
