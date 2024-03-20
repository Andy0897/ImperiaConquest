package com.example.ImperiaConquest.User;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapping) {
        this.userRepository = userRepository;
        this.userMapper = userMapping;
    }

    public String submitUser(UserDTO userDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors() || !comparePasswords(userDTO.getPassword(), userDTO.getRepeatPassword())) {
            model.addAttribute("user", userDTO);
            return "sign-up";
        }
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        return "redirect:/sign-in";
    }

    public boolean comparePasswords(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }
}