package org.example.demo_spring_mvc.controller;

import org.example.demo_spring_mvc.entity.Word;
import org.example.demo_spring_mvc.service.IDictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    private final IDictionaryService dictionaryService;

    public DictionaryController(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping({"/dictionary"})
    public String index() {
        return "dictionary/home-dictionary";
    }

    @PostMapping("/dictionary/translate")
    public String translate(@RequestParam("word") String word, Model model) {
        if (word == null || word.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập từ cần tra.");
            return "dictionary/result-dictionary";
        }

        Word w = dictionaryService.translate(word.trim());
        if (w == null) {
            model.addAttribute("error", "Không tìm thấy từ: " + word);
        } else {
            model.addAttribute("word", w.getKey());
            model.addAttribute("result", w.getMeaning());
        }
        return "dictionary/result-dictionary";
    }
}