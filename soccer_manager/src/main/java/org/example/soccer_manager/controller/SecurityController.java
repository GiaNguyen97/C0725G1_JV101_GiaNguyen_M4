package org.example.soccer_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {


    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "")String error) {
        model.addAttribute("error", error);
        return "security/login";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "security/logoutSuccessfulPage";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "security/403";
    }

}