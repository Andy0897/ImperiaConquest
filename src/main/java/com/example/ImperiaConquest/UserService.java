package com.example.ImperiaConquest;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String submitUser(UserDTO userDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
        }


    }
}
