package com.example.demo.home;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.test;
import com.example.demo.principal.PrincipalDetail;
import com.example.demo.user.user;



@Controller
@RequestMapping("/*")
public class homeController {
    @GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

    // @GetMapping("/")
	// public String home(Model model, @SessionAttribute(name = "name", required = false) String name) {
    //     if(name != null)
    //         model.addAttribute("name", name);
    //     else
    //         model.addAttribute("name", "no user");
	// 	return "home";
	// }
    
    @GetMapping("/")
	public String home(Model model, Authentication auth) {
        
        String name = null;
        if(auth != null)
            name = auth.getName();
        System.out.println(auth);
        if(name != null)
            model.addAttribute("name", name);
        else
            model.addAttribute("name", "no user");
		return "home";
	}

    @GetMapping("/json")
    public String json(@RequestBody test test, Model model) {
        model.addAttribute("test", test);
        
        return "json.html";
    }
    
}