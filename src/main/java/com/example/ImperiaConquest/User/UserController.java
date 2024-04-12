package com.example.ImperiaConquest.User;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    private EmpireService empireService;

    public UserController(UserRepository userRepository, UserService userService, EmpireService empireService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.empireService = empireService;
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
    public String submitUser(@ModelAttribute @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        return userService.submitUser(userDTO, bindingResult, model);
    }

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "sign-in";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        Empire empire = empireService.getEmpireByUsername(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("hasEmpire", (empire != null));
        model.addAttribute("empire", empire);
        return "profile";
    }
}