package com.example.demo.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.test;
import com.example.demo.customUserDetail.CustomDetail;
import com.example.demo.jwt.jwtProvider;
import com.example.demo.user.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
@RequestMapping("/")
public class homeController {
    
    @GetMapping("/")
	public String home(Model model, HttpServletRequest req, Authentication auth) {
        
        String name = (String)req.getAttribute("name");
        if(name != null) {
            model.addAttribute("type", "jwt");
        }
        model.addAttribute("name", name);
        if(auth != null) {
            // System.out.printf("auth : ");
            // System.out.println(auth);
            model.addAttribute("type", "session");
            model.addAttribute("name", auth.getName()); 
        }

		return "home";
	}

    @GetMapping("/json123")
    public String json(@RequestBody test test, Model model) {
        model.addAttribute("test", test);
        
        return "json";
    }
}