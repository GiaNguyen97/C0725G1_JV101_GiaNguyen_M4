package org.example.soccer_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.dto.SoccerPlayerSearchDTO;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.service.INationalTeamService;
import org.example.soccer_manager.service.ISoccerPlayerService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.Collectors;

import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/soccers")
@RequiredArgsConstructor
@SessionAttributes({"favoritePlayersSession", "playerSearchDTO"})
public class SoccerPlayerController {
    private final ISoccerPlayerService soccerPlayerService;
    private final INationalTeamService nationalTeamService;

    @ModelAttribute("favoritePlayersSession")
    public List<Long> initFavoritePlayers() {
        return new ArrayList<>();
    }

    @ModelAttribute("playerSearchDTO")
    public SoccerPlayerSearchDTO initSoccerPlayerSearchDTO() {
        return new SoccerPlayerSearchDTO();
    }

    @GetMapping("")
    public String showList(Model model,
                           @ModelAttribute("playerSearchDTO") SoccerPlayerSearchDTO searchDTO,
                           @ModelAttribute("favoritePlayersSession") List<Long> favoritePlayers,
                           @CookieValue(value = "favoritePlayers", defaultValue = "") String favoritePlayersCookie,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "searchPosition", required = false) String searchPosition,
                           @RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size) {

        if (favoritePlayers.isEmpty() && !favoritePlayersCookie.isEmpty()) {
            String decodedCookie = favoritePlayersCookie;
            try {
                decodedCookie = URLDecoder.decode(favoritePlayersCookie, StandardCharsets.UTF_8);
            } catch (Exception e) {
            }
            Arrays.stream(decodedCookie.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::parseLong)
                    .forEach(favoritePlayers::add);
        }

        if (page != null)
            searchDTO.setPage(page);
        if (size != null)
            searchDTO.setSize(size);

        Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(),
                Sort.by("codePlayer").ascending());

        model.addAttribute("soccers", soccerPlayerService.search(
                searchDTO.getName(),
                searchDTO.getDobFrom(),
                searchDTO.getDobTo(),
                searchDTO.getSearchPosition(),
                pageable));

        return "/soccer_players/list-card";
    }

    @GetMapping("/favorites")
    public String showFavorites(@ModelAttribute("favoritePlayersSession") List<Long> favoritePlayers, Model model) {
        List<SoccerPlayer> favorites = new ArrayList<>();
        if (!favoritePlayers.isEmpty()) {
            favorites = soccerPlayerService.findAllById(favoritePlayers);
        }
        model.addAttribute("soccers", new PageImpl<>(favorites));
        return "/soccer_players/favorites";
    }

    @GetMapping("/favorite/{id}")
    public String toggleFavorite(@PathVariable("id") Long id,
                                 @ModelAttribute("favoritePlayersSession") List<Long> favoritePlayers,
                                 HttpServletResponse response,
                                 @RequestHeader(value = "referer", required = false) String referer,
                                 RedirectAttributes redirectAttributes) {

        SoccerPlayer player = soccerPlayerService.findById(id);
        String name = (player != null) ? player.getNamePlayer() : "Cầu thủ";

        if (favoritePlayers.contains(id)) {
            favoritePlayers.remove(id);
            redirectAttributes.addFlashAttribute("success", "Đã xóa " + name + " khỏi danh sách yêu thích!");
        } else {
            favoritePlayers.add(id);
            redirectAttributes.addFlashAttribute("success", "Đã thêm " + name + " vào danh sách yêu thích!");
        }

        // Update Cookie
        String cookieValue = favoritePlayers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        try {
            cookieValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // Ignore if utf-8 not supported (unlikely)
        }

        Cookie cookie = new Cookie("favoritePlayers", cookieValue);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:" + (referer != null ? referer : "/soccers");
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));
        return "/soccer_players/detail";
    }

    @GetMapping("/add")
    public String formAddNewPlayer(Model model) {
        model.addAttribute("soccerPlayer", new SoccerPlayer());
        model.addAttribute("nationalTeams", nationalTeamService.findAll());
        return "/soccer_players/add";
    }

    @PostMapping("/add")
    public String addNewPlayer(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (soccerPlayerService.existsByCodePlayer(soccerPlayer.getCodePlayer())) {
            result.rejectValue("codePlayer", "duplicate", "Mã cầu thủ đã tồn tại");
        }

        if (soccerPlayer.getNationalTeam() != null) {
            try {
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(),
                        soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                model.addAttribute("nationalTeams", nationalTeamService.findAll());
                result.rejectValue("codePlayer", "duplicate", ex.getMessage());
                return "/soccer_players/add";
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("nationalTeams", nationalTeamService.findAll());
            return "/soccer_players/add";
        }

        if (soccerPlayerService.save(soccerPlayer) != null) {
            redirectAttributes.addFlashAttribute("success",
                    "Thêm mới cầu thủ " + soccerPlayer.getNamePlayer() + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error",
                    "Thêm mới cầu thủ " + soccerPlayer.getNamePlayer() + " thất bại!");
        }
        return "redirect:/soccers";
    }

    @GetMapping("/{id}/edit")
    public String formEditInfoPlayer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("soccerPlayer", soccerPlayerService.findById(id));
        model.addAttribute("nationalTeams", nationalTeamService.findAll());
        return "/soccer_players/edit";
    }

    @PostMapping("/edit")
    public String editInfoPlayer(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (soccerPlayerService.existsByCodePlayerAndIdNot(soccerPlayer.getCodePlayer(), soccerPlayer.getId())) {
            result.rejectValue("codePlayer", "codePlayer.duplicate", "Trùng mã với cầu thủ khác");
        }
        if (soccerPlayer.getNationalTeam() != null) {
            try {
                soccerPlayerService.validateCodePlayer(soccerPlayer.getCodePlayer(),
                        soccerPlayer.getNationalTeam().getCountryCode());
            } catch (IllegalArgumentException ex) {
                model.addAttribute("nationalTeams", nationalTeamService.findAll());
                result.rejectValue("codePlayer", "codePlayer.invalidFormat", ex.getMessage());
                return "/soccer_players/edit";
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("nationalTeams", nationalTeamService.findAll());
            return "/soccer_players/edit";
        }

        if (soccerPlayerService.save(soccerPlayer) != null) {
            redirectAttributes.addFlashAttribute("success",
                    "Cập nhập thông tin cầu thủ " + soccerPlayer.getNamePlayer() + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error",
                    "Cập nhập thông tin cầu thủ " + soccerPlayer.getNamePlayer() + " thất bại!");
        }

        return "redirect:/soccers";
    }

    @PostMapping("/{id}/delete")
    public String deleteSoccerPlayer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                     @RequestParam String namePlayer) {
        if (soccerPlayerService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("success", "Xóa cầu thủ " + namePlayer + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Xóa cầu thử " + namePlayer + " thất bại!");
        }
        return "redirect:/soccers";
    }

    @PostMapping("/{id}/register")
    public String registerToCompete(@Valid @ModelAttribute("soccerPlayer") SoccerPlayer soccerPlayer,
                                    RedirectAttributes redirectAttributes) {
        if (soccerPlayer.isPlayerStatus()) {
            soccerPlayer.setPlayerStatus(false);
            if (soccerPlayerService.save(soccerPlayer) != null) {
                redirectAttributes.addFlashAttribute("success",
                        "Chuyển trạng thái cầu thủ " + soccerPlayer.getNamePlayer() + " sang dự bị thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Chuyển trạng thái cầu thủ " + soccerPlayer.getNamePlayer() + " sang dự bị thất bại!");
            }
        } else {
            soccerPlayer.setPlayerStatus(true);
            if (soccerPlayerService.save(soccerPlayer) != null) {
                redirectAttributes.addFlashAttribute("success",
                        "Đăng ký thi đấu cho cầu thủ " + soccerPlayer.getNamePlayer() + " thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Đăng ký thi đấu cho cầu thủ " + soccerPlayer.getNamePlayer() + " thất bại!");
            }
        }
        return "redirect:/soccers";
    }

    @GetMapping("/lineup")
    public String showLineup(Model model) {
        model.addAttribute("lineup", soccerPlayerService.findAllByPlayerStatus(true));
        return "/soccer_players/lineup";
    }
}
