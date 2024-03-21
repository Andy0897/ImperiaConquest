package com.example.ImperiaConquest.User;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapping) {
        this.userRepository = userRepository;
        this.userMapper = userMapping;
    }

    public String submitUser(UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !comparePasswords(userDTO.getPassword(), userDTO.getRepeatPassword()) || checkIfExistsUserByUsername(userDTO.getUsername())) {
            model.addAttribute("user", userDTO);
            model.addAttribute("existsUserByUsername", checkIfExistsUserByUsername(userDTO.getUsername()));
            model.addAttribute("existsUserByEmail", checkIfExistsUserByEmail(userDTO.getEmail()));
            model.addAttribute("comparePasswords", !comparePasswords(userDTO.getPassword(), userDTO.getRepeatPassword()));
            return "sign-up";
        }
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        return "redirect:/sign-in";
    }

    public boolean checkIfExistsUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean checkIfExistsUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean comparePasswords(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }
}