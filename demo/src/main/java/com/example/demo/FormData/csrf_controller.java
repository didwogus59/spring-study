package com.example.demo.FormData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.test;

@Controller
@RequestMapping("/csrf")
public class csrf_controller {
    
    @RequestMapping(method = RequestMethod.GET)
    public String form_get(Model model) {
        model.addAttribute("test", new test());
        return "form/form_post_csrf";
    }

    
    @RequestMapping(method = RequestMethod.POST)
    public String form_post(@ModelAttribute test test, Model model) {
        model.addAttribute("test", test);
        System.out.print(test.getData());
        return "form/form_get";
    }
}
