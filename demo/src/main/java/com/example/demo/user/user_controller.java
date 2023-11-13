package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class user_controller {

    @Autowired
    private user_service service;

    @RequestMapping(path = "/sign", method = RequestMethod.GET)
    public String sign_get() {
        return "user/sign";
    }

    @RequestMapping(path = "/sign", method = RequestMethod.POST)
    public String sign_post(@ModelAttribute user user, Model model) {
        if(service.create_user(user))
            return "home";
        return "user/sign";
    }

    @RequestMapping(path = "/login/session", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(path = "/login/session", method = RequestMethod.POST)
    public String login_post(@ModelAttribute user user, HttpServletRequest httpServletRequest) {
        if(service.login_session(user)) {
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("name", user.getName());
            return "redirect:/";
        }
        else {
            return "user/login";
        }
    }

    @RequestMapping(path = "/logout/session", method = RequestMethod.GET)
    public String logout_session(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
