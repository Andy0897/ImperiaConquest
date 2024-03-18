package com.example.ImperiaConquest;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapping userMapping;

    public UserService(UserRepository userRepository, UserMapping userMapping) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
    }

    public String submitUser(UserDTO userDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "register";
        }
        User user = userMapping.toEntity(userDTO);
        userRepository.save(user);
        return "redirect:/login";
    }
}
