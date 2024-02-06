package com.example.demo.FormData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.test;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/form")
public class form_controller {
    
    @RequestMapping(method = RequestMethod.GET)
    public String form_get(Model model) {
        model.addAttribute("test", new test());
        return "form/form_post";
    }

    
    // @RequestMapping(method = RequestMethod.POST)
    // public String form_post(@ModelAttribute test test, Model model) {
    //     model.addAttribute("test", test);
    //    return "form/form_get";
    // }

    @RequestMapping(method = RequestMethod.POST)
    public String form_post(@RequestParam String title, @RequestParam String data, Model model) {
        model.addAttribute("test", new test(title, data));
       return "form/form_get";
    }

    // @RequestMapping(method = RequestMethod.POST)
    // public String form_post(HttpServletRequest req, Model model) {
    //     test test = new test(req.getParameter("title"), req.getParameter("data"));
    //     model.addAttribute("test", test);
    //     return "form/form_get";
    // }
}
