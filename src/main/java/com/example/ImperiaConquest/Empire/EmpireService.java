package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class EmpireService {
    UserRepository userRepository;
    EmpireRepository empireRepository;

    @Autowired
    public EmpireService(UserRepository userRepository, EmpireRepository empireRepository) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
    }

      public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails){
        if (bindingResult.hasErrors()) {
            return "empire/add";
        }

        User user = userRepository.getUserByUsername(myuserDetails.getUsername());
        empire.setUser(user);
        setResources(empire);
        empireRepository.save(empire);
        return "redirect:/empire/add";
    }
    private void setResources(Empire empire){
        empire.setGold(100);
        empire.setWood(100);
        empire.setIron(100);
    }
    public Empire getEmpireByUsername(String username){
        User user = userRepository.getUserByUsername(username);
        return empireRepository.getEmpireByUserId(user.getId());
    }
    public Long getEmpireWins(Empire empire){
        return (long) empire.getWins().size();
    }
}
