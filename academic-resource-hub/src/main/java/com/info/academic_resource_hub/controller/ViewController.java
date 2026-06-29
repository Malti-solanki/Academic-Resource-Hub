package com.info.academic_resource_hub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String homePage() {
        return "index"; // This looks for index.html in the templates folder
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // We will create this next
    }
}