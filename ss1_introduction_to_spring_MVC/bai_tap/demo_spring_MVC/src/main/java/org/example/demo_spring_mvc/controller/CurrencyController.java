package org.example.demo_spring_mvc.controller;

import org.example.demo_spring_mvc.entity.Currency;
import org.example.demo_spring_mvc.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

    private final ICurrencyService service;

    @Autowired
    public CurrencyController(ICurrencyService service) {
        this.service = service;
    }

    @GetMapping("/currency")
    public String currency() {
        return "currency/home-currency";
    }

    @PostMapping("/currency/convert")
    public String convert(
            @RequestParam("type") String type,
            @RequestParam("rate") double rate,
            @RequestParam("amount") double amount,
            Model model){

        Currency currency = new Currency(type, rate, amount);
        try {
            double result = service.convert(currency);

            model.addAttribute("result", result);
            model.addAttribute("type", type);
            model.addAttribute("rate", rate);
            model.addAttribute("amount", amount);

            if ("usd-to-vnd".equals(type)) {
                model.addAttribute("message", "Kết quả USD → VND");
                model.addAttribute("unitInput", "USD");
                model.addAttribute("unitOutput", "VND");
            } else {
                model.addAttribute("message", "Kết quả VND → USD");
                model.addAttribute("unitInput", "VND");
                model.addAttribute("unitOutput", "USD");
            }

            return "currency/result-currency";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "currency/home-currency";
        }
    }
}
