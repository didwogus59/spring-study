package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.jwt.jwtProvider;
import com.example.demo.principal.PrincipalDetail;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class user_controller {

    @Autowired
    private user_service service;

    @Autowired
    jwtProvider jwtProvider;

    private final  AuthenticationManagerBuilder authenticationManagerBuilder;


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

    // @RequestMapping(path = "/logout/session", method = RequestMethod.GET)
    // public String logout_session(HttpServletRequest req) {
    //     HttpSession session = req.getSession();
    //     session.invalidate();
    //     return "redirect:/";
    // }

    @RequestMapping(path = "/login/session", method = RequestMethod.GET)
    public String login_page_session() {
        return "user/login_session";
    }

    @RequestMapping(path = "/login/session", method = RequestMethod.POST)
    public String login_session(HttpServletRequest req, HttpServletResponse res) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UsernamePasswordAuthenticationToken token = null;
        try {
            token = new UsernamePasswordAuthenticationToken(name, password);
        } catch(Exception e) {
            return "redirect:/user/login/session"; 
        }
        // if(token.isAuthenticated() == false) {
        //     return "redirect:/user/login/session";    
        // }
        Authentication auth = authenticationManagerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }
    
    @RequestMapping(path = "/login/jwt", method = RequestMethod.GET)
    public String login_page_jwt() {
        return "user/login_jwt";
    }
    @RequestMapping(path = "/login/session/auth", method = RequestMethod.POST)
    public String login_session_complete() {
        return "redirect:/";
    }

    
    @RequestMapping(path = "/login/jwt/auth", method = RequestMethod.POST)
    public String login_jwt_complete() {
        return "redirect:/";
    }
    // @RequestMapping(path = "/login/jwt", method = RequestMethod.POST)
    // public String login_jwt(HttpServletRequest req, HttpServletResponse res) {
    //     String name = req.getParameter("name");
    //     String password = req.getParameter("password");
    //     if(service.login_jwt(new user(name,password))) {
    //         String token = jwtProvider.createToken(req.getParameter("name"));
    //         Cookie cookie = new Cookie("jwt", token);
    //         cookie.setPath("/");
    //         cookie.setHttpOnly(true);
    //         cookie.setMaxAge(60*60*24*7);
    //         res.addCookie(cookie);
    //         return "redirect:/";
    //     }
        
    //     return "redirect:/login/jwt";
    // }
    
}
