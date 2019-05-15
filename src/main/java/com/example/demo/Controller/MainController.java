package com.example.demo.Controller;

import com.example.demo.Model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    private Booking booking;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @GetMapping("/")
    public String index() {
        logger.info("index called.");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        logger.info("/login called.");
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        logger.info("user unable to log in");
        return "/error/403";
    }

    @GetMapping("/admin")
    public String admin(){
        logger.info("admin page called by: "+myAccessDeniedHandler.getUserName());
        return "/admin";
    }
}
