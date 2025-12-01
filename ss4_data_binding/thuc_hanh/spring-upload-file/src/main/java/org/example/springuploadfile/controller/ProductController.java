package org.example.springuploadfile.controller;


import org.example.springuploadfile.entity.Product;
import org.example.springuploadfile.entity.ProductForm;
import org.example.springuploadfile.service.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    private final IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null) {
            // Loại bỏ ký tự đặc biệt
            fileName = fileName.replaceAll("[\\\\/:*?\"<>|\\t]", "_");
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + "/" + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Product product = new Product(productForm.getId(), productForm.getName(),
                productForm.getDescription(), fileName);
        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm()); // reset form
        modelAndView.addObject("message", "Created new product successfully!");
        return modelAndView;
    }
}