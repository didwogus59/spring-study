package com.example.demo.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.test;

import org.springframework.ui.Model;

import java.util.Map;

@Controller
@RequestMapping("/mongoDB")
public class db_controller {
    @Autowired
    private db_service service;

    @RequestMapping(method = RequestMethod.GET)
    public String all_data(Model model) {
        model.addAttribute("testList", service.all_data());
        return "db/mongoDBboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create_data(@ModelAttribute test_db test, Model model) {
        service.create_data(test.getTitle(), test.getData());
        model.addAttribute("testList", service.all_data());
        return "db/mongoDBboard";
    }

    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String detail_data(@PathVariable ObjectId id, Model model) {
        
        test_db detail = service.get_data(id).get();
        model.addAttribute("detail", detail);
        model.addAttribute("childList", detail.getChilds());
        return "db/mongoDBDetail";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public String update_data(@PathVariable ObjectId id, @ModelAttribute test test, Model model) {
        
        test_db detail = service.update_data(id, test.getTitle(),test.getData());
        if(detail == null) {
            //check error later
            return "db/mongoDBDetail";
        }
        model.addAttribute("detail", detail);

        return "db/mongoDBDetail";
    }

    @RequestMapping(path = "/{id}/delete", method = RequestMethod.POST)
    public String delete_data(@PathVariable ObjectId id, Model model) {
        service.delete_data(id);
        return "redirect:/mongoDB";
    }
    
    @RequestMapping(path = "/{id}/child", method = RequestMethod.POST)
    public String create_child(@PathVariable ObjectId id, @RequestParam String data, Model model) {
        service.create_child2(id, data);
        return "redirect:/mongoDB/"+id;
    }
    
    //
    @RequestMapping(path = "/{id}/child/{child_id}/delete", method = RequestMethod.POST)
    public String delete_child(@PathVariable ObjectId id,@PathVariable ObjectId child_id, Model model) {
        service.delete_child(id, child_id);
        return "redirect:/mongoDB/"+id;
    }
}
