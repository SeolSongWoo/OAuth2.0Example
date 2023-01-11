package com.example.oauth2_0example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/")
    public String GotoMain() { return "redirect:/login";}

    @GetMapping("/login/signup")
    public ModelAndView signup() {




        return new ModelAndView("signup");
    }

}
