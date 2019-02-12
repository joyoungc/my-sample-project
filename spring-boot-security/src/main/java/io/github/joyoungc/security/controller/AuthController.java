package io.github.joyoungc.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(value = {"/home", "/"})
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
