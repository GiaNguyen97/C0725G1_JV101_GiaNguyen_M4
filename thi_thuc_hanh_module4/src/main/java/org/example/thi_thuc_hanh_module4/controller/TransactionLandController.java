package org.example.thi_thuc_hanh_module4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.thi_thuc_hanh_module4.entity.TransactionLand;
import org.example.thi_thuc_hanh_module4.service.ICustomerService;
import org.example.thi_thuc_hanh_module4.service.ITransactionLandService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lands")
public class TransactionLandController {
    private final ITransactionLandService transactionLandService;
    private final ICustomerService customerService;

    @GetMapping
    public String list(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "service", required = false) String service,
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {
        model.addAttribute("page", transactionLandService.search(name, service, pageable));
        model.addAttribute("transactionland", transactionLandService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return "list";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute TransactionLand transactionLand,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("error", "Vui lòng kiểm tra lại thông tin");
            return "redirect:/lands";
        }

        try {
            transactionLandService.save(transactionLand);
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }

        return "redirect:/lands";
    }
}
