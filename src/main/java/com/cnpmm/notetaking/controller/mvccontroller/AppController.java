package com.cnpmm.notetaking.controller.mvccontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/")
public class AppController {
    @GetMapping("dashboard")
    public String Dashboard(@RequestParam String token) {
        log.info("token: " + token);
        if (token != "000"){
            return "redirect:login";
        }
        return "dashboard";
    }

    @GetMapping("signup")
    public String Signup()
    {
        return "signup";

    }
}
