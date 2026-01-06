package org.example.soccer_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.service.INationalTeamService;
import org.example.soccer_manager.service.ISoccerPlayerService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/soccers")
@RequiredArgsConstructor
public class SoccerPlayerController {
    private final ISoccerPlayerService soccerPlayerService;
    private final INationalTeamService nationalTeamService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                        @RequestParam(value = "dobTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                        @RequestParam(value = "searchPosition", required = false) String searchPosition,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("codePlayer").ascending());

        model.addAttribute("soccers", soccerPlayerService.search(name, dobFrom, dobTo, searchPosition, pageable));
        model.addAttribute("name", name);
        model.addAttribute("searchPosition", searchPosition);
        model.addAttribute("dobFrom", dobFrom);
        model.addAttribute("dobTo", dobTo);
        model.addAttribute("size", size);

        return "/index";
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable("id") Long id, Model model,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                             @RequestParam(value = "searchPosition", required = false) String searchPosition,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));

        model.addAttribute("name", name);
        model.addAttribute("dobFrom", dobFrom);
        model.addAttribute("dobTo", dobTo);
        model.addAttribute("searchPosition", searchPosition);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "/detail";
    }

    @GetMapping("/add")
    public String formAddNewPlayer(Model model,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                                   @RequestParam(value = "searchPosition", required = false) String searchPosition,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        model.addAttribute("soccerPlayer", new SoccerPlayer());
        model.addAttribute("nationalTeams", nationalTeamService.findAll());
        model.addAttribute("name", name);
        model.addAttribute("dobFrom", dobFrom);
        model.addAttribute("dobTo", dobTo);
        model.addAttribute("searchPosition", searchPosition);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "/add";
    }

    @PostMapping("/add")
    public String addNewPlayer(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                               @RequestParam(value = "searchPosition", required = false) String searchPosition,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {

        if (soccerPlayerService.existsByCodePlayer(soccerPlayer.getCodePlayer())) {
            result.rejectValue("codePlayer", "duplicate", "Mã cầu thủ đã tồn tại");
        }

        if (result.hasErrors()) {
            return "/add"; // quay lại form
        }

        if (soccerPlayerService.save(soccerPlayer) != null) {
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại!");
        }
        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("dobFrom", dobFrom);
        redirectAttributes.addAttribute("dobTo", dobTo);
        redirectAttributes.addAttribute("searchPosition", searchPosition);
        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);
        return "redirect:/soccers";
    }

    @GetMapping("/{id}/edit")
    public String formEditInfoPlayer(@PathVariable("id") Long id, Model model,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                                     @RequestParam(value = "searchPosition", required = false) String searchPosition,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));
        model.addAttribute("nationalTeams", nationalTeamService.findAll());
        model.addAttribute("name", name);
        model.addAttribute("dobFrom", dobFrom);
        model.addAttribute("dobTo", dobTo);
        model.addAttribute("searchPosition", searchPosition);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "/edit";
    }

    @PostMapping("/edit")
    public String editInfoPlayer(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                                 @RequestParam(value = "searchPosition", required = false) String searchPosition,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        if (result.hasErrors()) {
            return "/edit"; // quay lại form
        }

        if (soccerPlayerService.save(soccerPlayer) != null) {
            redirectAttributes.addFlashAttribute("success", "Cập nhập thông tin thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cập nhập thông tin thất bại!");
        }

        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("dobFrom", dobFrom);
        redirectAttributes.addAttribute("dobTo", dobTo);
        redirectAttributes.addAttribute("searchPosition", searchPosition);
        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);

        return "redirect:/soccers";
    }

    @PostMapping("/{id}/delete")
    public String deleteSoccerPlayer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                                     @RequestParam(value = "searchPosition", required = false) String searchPosition,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        if (soccerPlayerService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("success", "Xóa thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Xóa thất bại!");
        }

        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("dobFrom", dobFrom);
        redirectAttributes.addAttribute("dobTo", dobTo);
        redirectAttributes.addAttribute("searchPosition", searchPosition);
        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);

        return "redirect:/soccers";
    }
}
