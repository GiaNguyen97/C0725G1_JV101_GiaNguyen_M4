package org.example.thi_thuc_hanh_module_4_again.controller;

import jakarta.validation.Valid;
import org.example.thi_thuc_hanh_module_4_again.entity.Product;
import org.example.thi_thuc_hanh_module_4_again.entity.Transation;
import org.example.thi_thuc_hanh_module_4_again.service.ICategoryService;
import org.example.thi_thuc_hanh_module_4_again.service.IProductService;
import org.example.thi_thuc_hanh_module_4_again.service.ITransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/transations")
public class TransationController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITransationService transationService;

    // Hiển thị danh sách + Tìm kiếm + Phân trang
    @GetMapping("")
    public String listTransations(Model model,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Transation> transationPage = transationService.findAll(dateFrom, dateTo, pageable);

        model.addAttribute("transationPage", transationPage);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products",productService.findAll());

        // Gửi lại các tham số tìm kiếm để giữ lại trên form
        model.addAttribute("searchDateFrom", dateFrom);
        model.addAttribute("searchDateTo", dateTo);
        model.addAttribute("size",size);

        return "transation/list";
    }


    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Transation> transation = transationService.findById(id);
        if (transation.isPresent()) {
            model.addAttribute("transation", transation.get());
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("products",productService.findAll());
            return "transation/edit";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy đơn hàng!");
            return "redirect:/transations";
        }
    }

    // Xử lý sửa
    @PostMapping("/edit")
    public String updateTransation(@Valid @ModelAttribute("transation") Transation transation,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("products", productService.findAll());
            return "transations/edit";
        }

        transationService.save(transation);
        redirectAttributes.addFlashAttribute("message", "Cập nhật đơn hàng thành công!");
        return "redirect:/transations";
    }
}
