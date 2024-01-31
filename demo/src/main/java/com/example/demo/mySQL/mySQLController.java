package com.example.demo.mySQL;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.test;
import com.example.demo.mongodb_test.test_db;

@Controller
@RequestMapping("/mysql")
public class mySQLController {
    
    @Autowired
    mySQLService service;
    
    @GetMapping()
    public String all_data(Model model) {
        model.addAttribute("testList", service.all_data());
        return "db/mySQLBoard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create_data(@ModelAttribute sqlEntity test, Model model) {
        service.create_data(test.getTitle(), test.getData());
        model.addAttribute("testList", service.all_data());
        return "db/mySQLBoard";
    }

    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String detail_data(@PathVariable Long id, Model model) {
        
        sqlEntity detail = service.get_data(id).get();
        model.addAttribute("detail", detail);
        model.addAttribute("childList", detail.getChilds());
        return "db/mySQLdetail";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public String update_data(@PathVariable Long id, @ModelAttribute sqlEntity test, Model model) {
        
        sqlEntity detail = service.update_data(id, test.getTitle(),test.getData());
        if(detail == null) {
            //check error later
            return "db/mySQLdetail";
        }
        model.addAttribute("detail", detail);

        return "db/mySQLdetail";
    }

    @RequestMapping(path = "/{id}/delete", method = RequestMethod.POST)
    public String delete_data(@PathVariable Long id, Model model) {
        service.delete_data(id);
        return "redirect:/mysql";
    }

    @RequestMapping(path = "/{id}/child", method = RequestMethod.POST)
    public String create_child(@PathVariable Long id, @RequestParam String data) {
        service.create_child(id, data);
        return "redirect:/mysql/" + id;
    }

    @RequestMapping(path = "/{id}/child/delete/{child_id}", method = RequestMethod.POST)
    public String delete_child(@PathVariable Long id,@PathVariable Long child_id, @RequestParam String data) {
        service.delete_child(child_id);
        return "redirect:/mysql/" + id;
    }}
