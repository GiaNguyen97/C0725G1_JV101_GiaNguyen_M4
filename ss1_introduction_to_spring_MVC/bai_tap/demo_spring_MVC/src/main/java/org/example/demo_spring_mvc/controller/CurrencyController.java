package org.example.demo_spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

    @GetMapping("/currency")
    public String currency() {
        return "currency/home-currency";
    }

    @PostMapping("/currency/convert")
    public String convert(
            @RequestParam("type") String type,
            @RequestParam("rate") double rate,
            @RequestParam("amount") double amount,
            Model model) {

        double result;

        if (rate <= 0 || amount < 0) {
            model.addAttribute("error", "Tỉ giá và số tiền phải lớn hơn 0!");
            return "currency/home-currency";
        }

        if (type.equals("usd-to-vnd")) {
            result = rate * amount;
            model.addAttribute("message", "Kết quả USD → VND");
            model.addAttribute("unitInput", "USD");
            model.addAttribute("unitOutput", "VND");
        } else {
            result = amount / rate;
            model.addAttribute("message", "Kết quả VND → USD");
            model.addAttribute("unitInput", "VND");
            model.addAttribute("unitOutput", "USD");
        }

        model.addAttribute("result", result);
        model.addAttribute("rate", rate);
        model.addAttribute("amount", amount);

        return "currency/result-currency";
    }
}