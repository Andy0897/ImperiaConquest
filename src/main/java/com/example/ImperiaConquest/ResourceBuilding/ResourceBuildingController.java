package com.example.ImperiaConquest.ResourceBuilding;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource-buildings")
public class ResourceBuildingController {
    ResourceBuildingService resourceBuildingService;
    UserRepository userRepository;
    EmpireService empireService;
    public ResourceBuildingController(ResourceBuildingService resourceBuildingService, UserRepository userRepository, EmpireService empireService) {
        this.resourceBuildingService = resourceBuildingService;
        this.userRepository = userRepository;
        this.empireService = empireService;
    }
}