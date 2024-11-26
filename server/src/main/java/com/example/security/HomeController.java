package com.example.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("nome", "user");
        return "index";
    };

    @GetMapping("/store")
    public String products(Model mode) {

        return "products";
    }

    @GetMapping("/chat")
    public String chat(Model model) {

        return "chat";
    }

    @GetMapping("/newProduct")
    public String newProduct(Model model) {

        return "newProduct";
    }
}
