package org.example.quan_ly_heo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.quan_ly_heo.entity.Origin;
import org.example.quan_ly_heo.service.OriginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/origins")
@RequiredArgsConstructor
public class OriginController {

    private final OriginService originService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("origins", originService.findAll());
        return "origin/origins";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Origin> getById(@PathVariable Long id) {
        return originService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Origin origin,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng kiểm tra lại thông tin");
            return "redirect:/origins";
        }

        try {
            originService.save(origin);
            redirectAttributes.addFlashAttribute("success", "Thêm xuất xứ thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }

        return "redirect:/origins";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Origin origin) {
        origin.setId(id);
        originService.save(origin);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            originService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa xuất xứ thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa xuất xứ đang được sử dụng!");
        }
        return "redirect:/origins";
    }
}
