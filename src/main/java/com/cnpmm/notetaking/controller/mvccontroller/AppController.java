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
    @GetMapping("new-note")
    public String Dashboard() {

        return "new-note";
    }

    @GetMapping("home")
    public String Home() {
        return "home";
    }
}
