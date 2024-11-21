package com.fiap.taligado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/initial_page")
    public String initialPage() {
        
        return "initial_page"; 
    }
}
