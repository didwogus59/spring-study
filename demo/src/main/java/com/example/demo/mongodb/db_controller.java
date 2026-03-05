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
public class MongoDbController {
    @Autowired
    private MongoDbService service;

    @RequestMapping(method = RequestMethod.GET)
    public String allData(Model model) {
        model.addAttribute("testList", service.allData());
        return "db/mongoDBboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createData(@ModelAttribute test_db test, Model model) {
        service.createData(test.getTitle(), test.getData());
        model.addAttribute("testList", service.allData());
        return "db/mongoDBboard";
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String detailData(@PathVariable ObjectId id, Model model) {

        test_db detail = service.getData(id).get();
        model.addAttribute("detail", detail);
        model.addAttribute("childList", detail.getChilds());
        return "db/mongoDBDetail";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public String updateData(@PathVariable ObjectId id, @ModelAttribute test test, Model model) {

        test_db detail = service.updateData(id, test.getTitle(),test.getData());
        if(detail == null) {
            //check error later
            return "db/mongoDBDetail";
        }
        model.addAttribute("detail", detail);

        return "db/mongoDBDetail";
    }

    @RequestMapping(path = "/{id}/delete", method = RequestMethod.POST)
    public String deleteData(@PathVariable ObjectId id, Model model) {
        service.deleteData(id);
        return "redirect:/mongoDB";
    }

    @RequestMapping(path = "/{id}/child", method = RequestMethod.POST)
    public String createChild(@PathVariable ObjectId id, @RequestParam String data, Model model) {
        service.createChild2(id, data);
        return "redirect:/mongoDB/"+id;
    }

    //
    @RequestMapping(path = "/{id}/child/{child_id}/delete", method = RequestMethod.POST)
    public String deleteChild(@PathVariable ObjectId id,@PathVariable ObjectId child_id, Model model) {
        service.deleteChild(id, child_id);
        return "redirect:/mongoDB/"+id;
    }
}
