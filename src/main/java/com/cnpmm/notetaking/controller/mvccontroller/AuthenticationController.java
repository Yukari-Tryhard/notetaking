package com.cnpmm.notetaking.controller.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Controller
@RequestMapping("/")
public class AuthenticationController {

    @GetMapping("login")
    public String Login(HttpSession session) {

        return "login";
    }

    @GetMapping("signup")
    public String Signup(){
        return "signup";
    }
}
