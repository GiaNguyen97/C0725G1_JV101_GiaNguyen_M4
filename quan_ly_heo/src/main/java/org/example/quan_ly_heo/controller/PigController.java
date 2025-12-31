package org.example.quan_ly_heo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.quan_ly_heo.entity.Pig;
import org.example.quan_ly_heo.repository.PigRepository;
import org.example.quan_ly_heo.service.OriginService;
import org.example.quan_ly_heo.service.PigService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PigController {
    private final PigService pigService;
    private final PigRepository pigRepository;
    private final OriginService originService;

    @GetMapping("/pigs")
    public String list(
            @RequestParam(required = false) Boolean sold,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Long originId,
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {
        model.addAttribute("page", pigService.search(sold, code, originId, pageable));
        model.addAttribute("origins", originService.findAll());
        return "pig/list";
    }

    @GetMapping("/pigs/{id}")
    @ResponseBody
    public ResponseEntity<Pig> getById(@PathVariable Long id) {
        Pig pig = pigService.findById(id);
        return ResponseEntity.ok(pig);
    }

    @PostMapping("/pigs")
    public String create(@Valid @ModelAttribute Pig pig,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("origins", originService.findAll());
            model.addAttribute("error", "Vui lòng kiểm tra lại thông tin");
            return "redirect:/pigs";
        }

        try {
            pigService.save(pig);
            redirectAttributes.addFlashAttribute("success", "Thêm heo thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }

        return "redirect:/pigs";
    }

    @PutMapping("/pigs/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Pig pig) {
        pig.setId(id);
        pigService.update(pig);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pigs/{id}")
    public String delete(@PathVariable Long id) {
        pigService.deleteById(id);
        return "redirect:/pigs";
    }

    @GetMapping("/pigs/top")
    public String top(
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        List<Pig> list = (size == 20)
                ? pigRepository.findTop20ByExportTimeIsNotNullOrderByExportWeightDesc()
                : pigRepository.findTop10ByExportTimeIsNotNullOrderByExportWeightDesc();

        model.addAttribute("list", list);
        model.addAttribute("size", size);
        return "pig/top";
    }
}
