package com.github.vjiki.wildsql.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParkController {

    @GetMapping("/park")
    public String parkMain(Model model) {
        return "park-main";
    }
}
