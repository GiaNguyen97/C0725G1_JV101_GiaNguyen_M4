package org.example.soccer_manager_jakarta.controller;

import org.example.soccer_manager_jakarta.entity.SoccerPlayer;
import org.example.soccer_manager_jakarta.service.ISoccerPlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/soccers")
public class SoccerPlayerController {
    private final ISoccerPlayerService soccerPlayerService;

    public  SoccerPlayerController(ISoccerPlayerService soccerPlayerService) {
        this.soccerPlayerService = soccerPlayerService;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("soccers",soccerPlayerService.findAll());
        return "/index";
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable("id") Long id, Model model){
        model.addAttribute("soccer_player",soccerPlayerService.findById(id));
        return "/detail";
    }

    @GetMapping("/add")
    public String formAddNewPlayer(Model model){
        model.addAttribute("soccer_player",new SoccerPlayer());
        return "/add";
    }

    @PostMapping("/add")
    public String addNewPlayer(SoccerPlayer soccerPlayer, RedirectAttributes redirectAttributes){
        if (soccerPlayerService.addNewPlayer(soccerPlayer)){
            redirectAttributes.addFlashAttribute("success","Thêm mới thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error","Thêm mới thất bại!");
        }
        return "redirect:/soccers";
    }

    @GetMapping("/{id}/edit")
    public String formEditInfoPlayer(@PathVariable("id") Long id, Model model){
        model.addAttribute("soccer_player",soccerPlayerService.findById(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String editInfoPlayer(SoccerPlayer soccerPlayer, RedirectAttributes redirectAttributes){
        if (soccerPlayerService.editInfoPlayer(soccerPlayer)) {
            redirectAttributes.addFlashAttribute("success","Cập nhập thông tin thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error","Cập nhập thông tin thất bại!");
        }
        return "redirect:/soccers";
    }

    @PostMapping("/{id}/delete")
    public String deleteSoccerPlayer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        if(soccerPlayerService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("success","Xóa thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error","Xóa thất bại!");
        }
        return "redirect:/soccers";
    }
}
