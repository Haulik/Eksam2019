package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class MainController {


    @GetMapping("/")
    public String index() {

        return "index";

    }
}
