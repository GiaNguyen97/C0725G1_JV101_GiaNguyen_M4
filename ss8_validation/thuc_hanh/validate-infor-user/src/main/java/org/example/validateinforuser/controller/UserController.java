package org.example.validateinforuser.controller;

import org.example.validateinforuser.dto.UserDto;
import org.example.validateinforuser.entity.User;
import org.example.validateinforuser.validation.Step1;
import org.example.validateinforuser.validation.Step2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {


    @Autowired
    private SmartValidator validator;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "index";
    }

    @PostMapping("/register")
    public String register(
            @Validated(Step1.class) @ModelAttribute("user") UserDto userDto,
            BindingResult resultStep1,
            Model model) {

        // Validate step 1
        if (resultStep1.hasErrors()) {
            return "index";
        }

        // Validate step 2
        validator.validate(userDto, resultStep1, Step2.class);

        if (resultStep1.hasErrors()) {
            return "index";
        }

        // Copy to entity
        User user = new User();
        BeanUtils.copyProperties(userDto, user);


        model.addAttribute("user", user);
        return "result";
    }
}
