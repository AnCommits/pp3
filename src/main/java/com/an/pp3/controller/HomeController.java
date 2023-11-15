package com.an.pp3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() {
        return "redirect:users";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }
}
