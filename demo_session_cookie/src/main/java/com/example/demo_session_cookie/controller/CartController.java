package com.example.demo_session_cookie.controller;

import com.example.demo_session_cookie.entity.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("cart")
public class CartController {

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@ModelAttribute("cart") Cart cart){
        ModelAndView modelAndView = new ModelAndView("views/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }
}
