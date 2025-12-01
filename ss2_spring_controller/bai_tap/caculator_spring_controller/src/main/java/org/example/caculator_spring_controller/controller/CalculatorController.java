package org.example.caculator_spring_controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operator") String operator,
            Model model
    ) {
        double result = 0;

        switch (operator) {
            case "add":
                result = num1 + num2;
                break;
            case "sub":
                result = num1 - num2;
                break;
            case "mul":
                result = num1 * num2;
                break;
            case "div":
                if (num2 == 0) {
                    model.addAttribute("error", "Không thể chia cho 0");
                    model.addAttribute("num1", num1);
                    model.addAttribute("num2", num2);
                    model.addAttribute("operator", operator);
                    return "home";
                }
                result = num1 / num2;
        }

        model.addAttribute("result", result);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operator", operator);

        return "home";
    }
}