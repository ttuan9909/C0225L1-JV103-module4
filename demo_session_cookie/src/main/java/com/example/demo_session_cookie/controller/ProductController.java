package com.example.demo_session_cookie.controller;

import com.example.demo_session_cookie.entity.Cart;
import com.example.demo_session_cookie.entity.Product;
import com.example.demo_session_cookie.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
        private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("cart")
        public Cart setupCart() {
            return new Cart();
        }

        @GetMapping("/shop")
        public ModelAndView showShop() {
            ModelAndView modelAndView = new ModelAndView("views/shop");
            modelAndView.addObject("products", productService.findAll());
            return modelAndView;
        }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id,
                            @ModelAttribute("cart") Cart cart,
                            @RequestParam(value = "action", required = false) String action,
                            RedirectAttributes ra) {
        Optional<Product> productOptional = Optional.ofNullable(productService.findById(id));
        if (productOptional.isEmpty()) {
            return "views/error";
        }

        Product p = productOptional.get();
        cart.addProduct(p);

        ra.addFlashAttribute("addedOk", true);
        ra.addFlashAttribute("addedName", p.getName());
        ra.addFlashAttribute("addedPrice", p.getPrice());

        if ("show".equalsIgnoreCase(action)) {
            return "redirect:/shopping-cart";
        }
        return "redirect:/shop";
    }

    @GetMapping("/product/{id}")
    public ModelAndView productDetail(@PathVariable int id) {
        Product p = productService.findById(id);
        if (p == null) return new ModelAndView("views/error");
        ModelAndView mv = new ModelAndView("views/detail");
        mv.addObject("product", p);
        return mv;
    }
    }