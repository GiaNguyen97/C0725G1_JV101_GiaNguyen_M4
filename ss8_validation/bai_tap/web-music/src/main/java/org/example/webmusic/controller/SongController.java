package org.example.webmusic.controller;

import lombok.RequiredArgsConstructor;
import org.example.webmusic.dto.SongDto;
import org.example.webmusic.entity.Song;
import org.example.webmusic.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("song", new SongDto());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadSong(@ModelAttribute("song") SongDto songDto, Model model,
                                RedirectAttributes redirectAttributes) {

        MultipartFile file = songDto.getFile();

        if (file.isEmpty()) {
            model.addAttribute("error", "Vui lòng chọn file!");
            return "upload";
        }

        if (!songService.isValidFile(file)) {
            model.addAttribute("error", "Chỉ chấp nhận file .mp3 .wav .ogg .m4p");
            return "upload";
        }

        try {
            songService.saveSong(songDto.getName(), songDto.getArtist(), songDto.getCategory(), file);
            redirectAttributes.addFlashAttribute("msg", "Upload bài hát thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi upload file!");
            return "upload";
        }

        return "redirect:/songs";
    }

    @GetMapping("")
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "song-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Song song,
                         @RequestParam("fileMusic") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        songService.update(id, song, file);
        redirectAttributes.addFlashAttribute("msg", "Cập nhập bài hát thành công!");
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        songService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Xóa bài hát thành công!");
        return "redirect:/songs";
    }
}