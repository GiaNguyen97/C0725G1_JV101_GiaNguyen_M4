package org.example.soccer_manager_jakarta.controller;

import java.time.LocalDate;
import jakarta.validation.Valid;
import org.example.soccer_manager_jakarta.entity.SoccerPlayer;
import org.example.soccer_manager_jakarta.service.ISoccerPlayerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/soccers")
public class SoccerPlayerController {
    private final ISoccerPlayerService soccerPlayerService;

    public SoccerPlayerController(ISoccerPlayerService soccerPlayerService) {
        this.soccerPlayerService = soccerPlayerService;
    }

    @GetMapping("")
    public String index(Model model,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo) {
        model.addAttribute("soccers", soccerPlayerService.search(name, dobFrom, dobTo));
        model.addAttribute("name", name);
        model.addAttribute("dobFrom", dobFrom);
        model.addAttribute("dobTo", dobTo);
        return "/index";
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("soccer_player", soccerPlayerService.findById(id));
        return "/detail";
    }

    @GetMapping("/add")
    public String formAddNewPlayer(Model model) {
        model.addAttribute("soccer_player", new SoccerPlayer());
        return "/add";
    }

    @PostMapping("/add")
    public String addNewPlayer(@Valid @ModelAttribute("soccer_player") SoccerPlayer soccerPlayer,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (soccerPlayerService.findByCode(soccerPlayer.getCodePlayer()) != null) {
            result.rejectValue("codePlayer", "duplicate", "Mã cầu thủ đã tồn tại");
        }

        if (result.hasErrors()) {
            return "/add"; // quay lại form
        }

        if (soccerPlayerService.addNewPlayer(soccerPlayer)) {
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại!");
        }
        return "redirect:/soccers";
    }

    @GetMapping("/{id}/edit")
    public String formEditInfoPlayer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("soccer_player", soccerPlayerService.findById(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String editInfoPlayer(@Valid @ModelAttribute("soccer_player") SoccerPlayer soccerPlayer,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/edit"; // quay lại form
        }

        if (soccerPlayerService.editInfoPlayer(soccerPlayer)) {
            redirectAttributes.addFlashAttribute("success", "Cập nhập thông tin thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cập nhập thông tin thất bại!");
        }

        return "redirect:/soccers";
    }

    @PostMapping("/{id}/delete")
    public String deleteSoccerPlayer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (soccerPlayerService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("success", "Xóa thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Xóa thất bại!");
        }
        return "redirect:/soccers";
    }
}
