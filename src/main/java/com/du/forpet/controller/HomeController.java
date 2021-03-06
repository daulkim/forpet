package com.du.forpet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/helpers/login")
    public String helperLogin() {
        return "helper/login";
    }

    @PostMapping("/authenticate")
    public String auth() {
        return "login";
    }

    @PostMapping("/helpers/authenticate")
    public String helperAuth() {
        return "helper/login";
    }

}

