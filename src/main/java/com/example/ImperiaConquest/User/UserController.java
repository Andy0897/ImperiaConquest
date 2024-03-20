package com.example.ImperiaConquest.User;

import jakarta.validation.Valid;
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

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String getHome() {
        return "home";
    }

    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "sign-up";
    }

    @PostMapping("/submit-user")
    public String submitUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Model model) {
        return userService.submitUser(userDTO, bindingResult, model);
    }

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "sign-in";
    }
}