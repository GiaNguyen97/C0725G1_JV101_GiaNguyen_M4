package org.example.my_blog.controller;

import org.example.my_blog.entity.Blog;
import org.example.my_blog.service.IBlogService;
import org.example.my_blog.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;
    private final ICategoryService categoryService;

    public BlogController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }


    @GetMapping("")
    public String list( @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size,
                        Model model) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        Page<Blog> blogPage = blogService.findAll(pageable);
        model.addAttribute("blogs", blogPage);
//        model.addAttribute("categories",categoryService.findAll());
        return "blogs/list";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blogs/create";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute Blog blog,
                         @RequestParam("imageFile") MultipartFile imageFile) {

        if (!imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" +
                        imageFile.getOriginalFilename();

                Path path = Paths.get("E:/CODEGYM/bai_tap_code_gym/module_4/ss6_JPA/bai_tap/my_blog/img/" + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, imageFile.getBytes());

                blog.setImg("/img/" + fileName); // 🔑 URL dùng để hiển thị
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        blogService.save(blog);
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

        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.findAll());
        return "blogs/edit";
    }


    @PostMapping("/edit")
    public String update(@ModelAttribute Blog blog,
                         @RequestParam("imageFile") MultipartFile imageFile) {

        Blog oldBlog = blogService.findById(blog.getId());

        // 👉 Chỉ xử lý ảnh khi có upload mới
        if (!imageFile.isEmpty()) {
            try {
                // (tuỳ chọn) xóa ảnh cũ
                if (oldBlog.getImg() != null) {
                    Path oldPath = Paths.get("E:/CODEGYM/bai_tap_code_gym/module_4/ss6_JPA/bai_tap/my_blog/img/" +
                            oldBlog.getImg().replace("/img/", ""));
                    Files.deleteIfExists(oldPath);
                }

                String fileName = System.currentTimeMillis() + "_" +
                        imageFile.getOriginalFilename();

                Path newPath = Paths.get("E:/CODEGYM/bai_tap_code_gym/module_4/ss6_JPA/bai_tap/my_blog/img/" + fileName);
                Files.write(newPath, imageFile.getBytes());

                oldBlog.setImg("/img/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 👉 Update dữ liệu khác
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setContent(blog.getContent());
        oldBlog.setCategory(blog.getCategory());

        blogService.save(oldBlog);
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

    @GetMapping("/search")
    public String search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 5);
        Page<Blog> blogPage = blogService.findByTitleContaining(keyword, pageable);

        model.addAttribute("blogs", blogPage);
        model.addAttribute("keyword", keyword);
        return "post/list";
    }
}
