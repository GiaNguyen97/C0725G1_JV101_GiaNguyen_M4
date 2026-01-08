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
    public String showList(Model model,
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

        return "/soccer_players/list";
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

        return "/soccer_players/detail";
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
        return "/soccer_players/add";
    }

    @PostMapping("/add")
    public String addNewPlayer(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                               BindingResult result,
                               Model model,
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

        if (soccerPlayer.getNationalTeam() != null) {
            try {
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(), soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                model.addAttribute("nationalTeams", nationalTeamService.findAll());
                result.rejectValue("codePlayer", "duplicate", ex.getMessage());
                return "/soccer_players/add";
            }
        }


        if (result.hasErrors()) {
            model.addAttribute("nationalTeams", nationalTeamService.findAll());
            model.addAttribute("name", name);
            model.addAttribute("dobFrom", dobFrom);
            model.addAttribute("dobTo", dobTo);
            model.addAttribute("searchPosition", searchPosition);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            return "/soccer_players/add"; // quay lại form
        }

        if (soccerPlayerService.save(soccerPlayer) != null) {
            redirectAttributes.addFlashAttribute("success", "Thêm mới cầu thủ " + soccerPlayer.getNamePlayer() + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Thêm mới cầu thủ " + soccerPlayer.getNamePlayer() + " thất bại!");
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
        return "/soccer_players/edit";
    }

    @PostMapping("/edit")
    public String editInfoPlayer(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                                 @RequestParam(value = "searchPosition", required = false) String searchPosition,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        if (soccerPlayerService.existsByCodePlayerAndIdNot(soccerPlayer.getCodePlayer(), soccerPlayer.getId())) {
            result.rejectValue("codePlayer", "codePlayer.duplicate", "Trùng mã với cầu thủ khác");
        }
        if (soccerPlayer.getNationalTeam() != null) {
            try {
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(), soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                model.addAttribute("nationalTeams", nationalTeamService.findAll());
                result.rejectValue("codePlayer", "codePlayer.invalidFormat", ex.getMessage());
                return "/soccer_players/edit";
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("nationalTeams", nationalTeamService.findAll());
            model.addAttribute("name", name);
            model.addAttribute("dobFrom", dobFrom);
            model.addAttribute("dobTo", dobTo);
            model.addAttribute("searchPosition", searchPosition);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            return "/soccer_players/edit"; // quay lại form
        }

        if (soccerPlayerService.save(soccerPlayer) != null) {
            redirectAttributes.addFlashAttribute("success", "Cập nhập thông tin cầu thủ " + soccerPlayer.getNamePlayer() + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cập nhập thông tin cầu thủ " + soccerPlayer.getNamePlayer() + " thất bại!");
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
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam String namePlayer) {
        if (soccerPlayerService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("success", "Xóa cầu thủ " + namePlayer + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Xóa cầu thử " + namePlayer + " thất bại!");
        }

        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("dobFrom", dobFrom);
        redirectAttributes.addAttribute("dobTo", dobTo);
        redirectAttributes.addAttribute("searchPosition", searchPosition);
        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);

        return "redirect:/soccers";
    }

    @PostMapping("/{id}/register")
    public String registerToCompete(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                                    RedirectAttributes redirectAttributes,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobFrom,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dobTo,
                                    @RequestParam(value = "searchPosition", required = false) String searchPosition,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        if (soccerPlayer.isPlayerStatus()) {
            soccerPlayer.setPlayerStatus(false);
            if (soccerPlayerService.save(soccerPlayer) != null) {
                redirectAttributes.addFlashAttribute("success", "Chuyển trạng thái cầu thủ " + soccerPlayer.getNamePlayer() + " sang dự bị thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Chuyển trạng thái cầu thủ " + soccerPlayer.getNamePlayer() + " sang dự bị thất bại!");
            }
        } else {
            soccerPlayer.setPlayerStatus(true);
            if (soccerPlayerService.save(soccerPlayer) != null) {
                redirectAttributes.addFlashAttribute("success", "Đăng ký thi đấu cho cầu thủ " + soccerPlayer.getNamePlayer() + " thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Đăng ký thi đấu cho cầu thủ " + soccerPlayer.getNamePlayer() + " thất bại!");
            }
        }

        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("dobFrom", dobFrom);
        redirectAttributes.addAttribute("dobTo", dobTo);
        redirectAttributes.addAttribute("searchPosition", searchPosition);
        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);
        return "redirect:/soccers";
    }

    @GetMapping("/lineup")
    public String showLineup(Model model) {
        model.addAttribute("lineup",soccerPlayerService.findAllByPlayerStatus(true));
        return "/soccer_players/lineup";
    }
}
