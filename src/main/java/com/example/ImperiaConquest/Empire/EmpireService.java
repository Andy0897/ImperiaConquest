package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Constants.ResourceTypes;
import com.example.ImperiaConquest.Resource.Resource;
import com.example.ImperiaConquest.Resource.ResourceService;
import com.example.ImperiaConquest.Resource.ResourcesRepository;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class EmpireService {
    UserRepository userRepository;
    EmpireRepository empireRepository;
    ResourcesRepository resourcesRepository;

    @Autowired
    public EmpireService(UserRepository userRepository, EmpireRepository empireRepository, ResourcesRepository resourcesRepository) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
        this.resourcesRepository = resourcesRepository;
    }

      public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails){
        if (bindingResult.hasErrors()) {
            return "empire/add";
        }

        User user = userRepository.getUserByUsername(myuserDetails.getUsername());
        empire.setUser(user);
        empireRepository.save(empire);
        setResources(empire);
        return "redirect:/empire/add";
    }
    private void setResources(Empire empire){
        ResourceService resourceService = new ResourceService(resourcesRepository);
        resourceService.createResource(empire, ResourceTypes.GOLD, 100);
        resourceService.createResource(empire, ResourceTypes.WOOD, 100);
        resourceService.createResource(empire, ResourceTypes.IRON, 100);

    }
    public Empire getEmpireByUsername(String username){
        User user = userRepository.getUserByUsername(username);
        return empireRepository.getEmpireByUserId(user.getId());
    }
}
