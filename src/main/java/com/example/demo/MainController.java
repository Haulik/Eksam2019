package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class MainController {


    @GetMapping("/")
    public String index() {

        return "index";

    }
    @GetMapping("/login")
    public String login() {

        return "login";

    }

    @GetMapping("/calender")
    public String calender() {

        return "calender";

    }
}
