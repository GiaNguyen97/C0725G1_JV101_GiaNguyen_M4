package org.example.shoppingcart.controller;

import org.example.shoppingcart.entity.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingController {
    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@SessionAttribute("cart") Cart cart){
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }

    @GetMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable Long id,
                             @SessionAttribute("cart") Cart cart) {

        cart.getProducts().entrySet().removeIf(
                entry -> entry.getKey().getId().equals(id)
        );

        return "redirect:/shopping-cart";
    }

    /** Update số lượng */
    @GetMapping("/cart/update/{id}")
    public String updateQuantity(@PathVariable Long id,
                                 @RequestParam int quantity,
                                 @SessionAttribute("cart") Cart cart) {

        if (quantity <= 0) {
            // Xóa luôn nếu số lượng <= 0
            cart.getProducts().entrySet().removeIf(
                    entry -> entry.getKey().getId().equals(id)
            );
        } else {
            // Cập nhật số lượng
            cart.getProducts().entrySet().forEach(entry -> {
                if (entry.getKey().getId().equals(id)) {
                    entry.setValue(quantity);
                }
            });
        }

        return "redirect:/shopping-cart";
    }
}