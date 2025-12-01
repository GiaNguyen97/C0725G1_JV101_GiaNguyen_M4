package org.example.mail_settings.controller;

import org.example.mail_settings.entity.MailSettings;
import org.example.mail_settings.service.MailSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class MailSettingsController {

    private final MailSettingsService mailSettingsService;

    public MailSettingsController(MailSettingsService mailSettingsService) {
        this.mailSettingsService = mailSettingsService;
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(value="lang", required=false) String lang) {
        MailSettings settings = mailSettingsService.getSettings();
        if(lang != null) settings.setLanguage(lang);
        model.addAttribute("mailSettings", settings);
        return "settings";
    }

    @PostMapping
    public String updateSettings(@ModelAttribute MailSettings mailSettings) {
        mailSettingsService.update(mailSettings);
        return "redirect:/settings/result?lang=" + mailSettings.getLanguage();
    }

    @GetMapping("/result")
    public String showResult(@RequestParam("lang") String lang, Model model) {
        MailSettings settings = mailSettingsService.getSettings();
        settings.setLanguage(lang);
        model.addAttribute("mailSettings", settings);
        model.addAttribute("lang", lang);
        return "result";
    }
}
