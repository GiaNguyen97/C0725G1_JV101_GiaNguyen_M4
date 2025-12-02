package org.example.product_manager_thymeleaf.controller;

import org.example.product_manager_thymeleaf.entity.Product;
import org.example.product_manager_thymeleaf.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // Danh sách
    @GetMapping
    public String list(Model model,
                       @RequestParam(value = "toastMessage", required = false) String toastMessage,
                       @RequestParam(value = "toastType", required = false) String toastType) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("toastMessage", toastMessage);
        model.addAttribute("toastType", toastType);
        return "/products/list";
    }

    // Form tạo
    @GetMapping("/add")
    public String showCreate(Model model) {
        model.addAttribute("product", new Product());
        return "/products/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Product product, RedirectAttributes ra) {
        boolean result = productService.save(product);
        if (result) {
            ra.addAttribute("toastMessage", "Thêm sản phẩm thành công!");
            ra.addAttribute("toastType", "text-bg-success");
        } else {
            ra.addAttribute("toastMessage", "Thêm sản phẩm thất bại!");
            ra.addAttribute("toastType", "text-bg-danger");
        }
        return "redirect:/products";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/products/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Product product, RedirectAttributes ra) {
        boolean result = productService.update(product.getId(), product);
        if (result) {
            ra.addAttribute("toastMessage", "Sửa sản phẩm thành công!");
            ra.addAttribute("toastType", "text-bg-warning");
        } else {
            ra.addAttribute("toastMessage", "Sửa sản phẩm thất bại!");
            ra.addAttribute("toastType", "text-bg-danger");
        }
        return "redirect:/products";
    }

    // Xóa
    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id, RedirectAttributes ra) {
        boolean result = productService.delete(id);
        if (result) {
            ra.addAttribute("toastMessage", "Xoá sản phẩm thành công!");
            ra.addAttribute("toastType", "text-bg-danger");
        } else {
            ra.addAttribute("toastMessage", "Xoá sản phẩm thất bại!");
            ra.addAttribute("toastType", "text-bg-danger");
        }
        return "redirect:/products";
    }

    // Chi tiết
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/products/detail";
    }

    // Tìm kiếm
    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("products", productService.searchByName(name));
        return "/products/list";
    }
}
