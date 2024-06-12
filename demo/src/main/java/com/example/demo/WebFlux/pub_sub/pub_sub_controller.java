package com.example.demo.WebFlux.pub_sub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.test;

@Controller
@RequestMapping("/pubsub")
public class pub_sub_controller {

    @RequestMapping(method = RequestMethod.GET)
    public String form_get(Model model) {
        my_pub pub = new my_pub();
        my_sub sub = new my_sub();

        pub.subscribe(sub);
        return "home";
    }
}
