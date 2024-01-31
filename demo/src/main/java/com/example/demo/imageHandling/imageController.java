package com.example.demo.imageHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.imageHandling.entity.image;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;


@Controller
@RequestMapping("/image")
public class imageController {
    
    @Autowired
    private imageService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        service.saveImage(file);
        return ResponseEntity.ok("Image uploaded successfully");
    }

    @GetMapping("/show/{id}")
    public String showImage(@PathVariable Long id, Model model) {
        
        image tmp = service.getImage(id);
        String base64Image = Base64.getEncoder().encodeToString(tmp.getData());
        model.addAttribute("image", base64Image);
        return "file/display";
    }
    @GetMapping("/test")
    public String getMethodName() {
        
        return "file/upload";
    }
    
}
