package org.example.my_blog.controller;

import org.example.my_blog.entity.Blog;
import org.example.my_blog.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;

    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

        @GetMapping("")
    public String list(Model model) {
        model.addAttribute("pageTitle", "Danh sách blog");
        model.addAttribute("blogs", blogService.findAll());
        return "blogs/list";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Tạo blog mới");
        model.addAttribute("blog", new Blog());
        return "blogs/create";
    }


    @PostMapping("/create")
    public String save(@ModelAttribute Blog blog,
                       RedirectAttributes redirectAttributes) {

        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Tạo blog thành công!");

        return "redirect:/blogs";
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model,
                         RedirectAttributes redirectAttributes) {

        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Blog không tồn tại!");
            return "redirect:/blogs";
        }

        model.addAttribute("pageTitle", "Chi tiết: " + blog.getTitle());
        model.addAttribute("blog", blog);

        return "blogs/detail";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model,
                           RedirectAttributes redirectAttributes) {

        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy blog để sửa!");
            return "redirect:/blogs";
        }

        model.addAttribute("pageTitle", "Sửa: " + blog.getTitle());
        model.addAttribute("blog", blog);

        return "blogs/edit";
    }


    @PostMapping("/edit")
    public String update(@ModelAttribute Blog blog,
                         RedirectAttributes redirectAttributes) {

        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Cập nhật blog thành công!");

        return "redirect:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {

        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy blog để xóa!");
            return "redirect:/blogs";
        }

        blogService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Đã xóa blog: " + blog.getTitle());

        return "redirect:/blogs";
    }
}
