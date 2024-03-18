package com.example.ImperiaConquest;

import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/submit-user")
    public String submitUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Model model) {
        return
    }
}
