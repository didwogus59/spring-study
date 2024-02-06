package com.example.demo.returnType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/return")
public class returnController {
    
    @GetMapping("/ModelAndView")
    public ModelAndView test1() {
        ModelAndView mav = new ModelAndView("return/test");
        mav.addObject("data", "model and view");
        return mav;
    }
}
