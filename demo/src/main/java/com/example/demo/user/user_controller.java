package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.principal.PrincipalDetail;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(path = "/user")
public class user_controller {

    @Autowired
    private user_service service;

    @RequestMapping(path = "/data", method = RequestMethod.GET)
    public String user_data(Model model) {
        model.addAttribute("user", new user());
        return "user/data";
    }

    @RequestMapping(path = "/test1", method = RequestMethod.GET)
    public String test1(Authentication auth, @AuthenticationPrincipal PrincipalDetail userDetail) {
        System.out.println(auth);
        System.out.println("------------------------------");
        PrincipalDetail detail = (PrincipalDetail)auth.getPrincipal();
        System.out.println(auth.getPrincipal());
        
        System.out.println("------------------------------");
        
        System.out.println(detail);
        System.out.println("------------------------------");
        
        System.out.println(detail.getUsername());
        System.out.println("------------------------------");
        
        System.out.println(userDetail.getUsername());
        return "user/data";
    }


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

    // @RequestMapping(path = "/login/session", method = RequestMethod.POST)
    // public String login_post(@ModelAttribute user user, HttpServletRequest httpServletRequest) {
    //     if(service.login_session(user)) {
    //         HttpSession session = httpServletRequest.getSession(true);
    //         session.setAttribute("name", user.getName());
    //         return "redirect:/";
    //     }
    //     else {
    //         return "user/login";
    //     }
    // }

    @RequestMapping(path = "/logout/session", method = RequestMethod.GET)
    public String logout_session(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
