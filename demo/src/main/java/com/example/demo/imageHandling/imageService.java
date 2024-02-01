package com.example.demo.imageHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.imageHandling.entity.image;
import com.example.demo.imageHandling.repository.imageRepository;

import jakarta.annotation.Resource;

import java.util.*;

@Service
public class imageService {
    
    String[] types = {"jpg", "jpeg", "png", "gif"};

    @Autowired
    private imageRepository repo;

    // @Resource
    // private JdbcTemplate imageTemplate;

    // @Autowired
    // private JdbcTemplate template;
    public List<Long> imageList() {
        return repo.findAllId();
    }
    public boolean saveImage(MultipartFile file) {
        if(typeCheck(file.getOriginalFilename())) {
            image image = new image();
            image.setName(file.getOriginalFilename());
        try {
            image.setData(file.getBytes());
            repo.save(image);
            return true;
        } catch(Exception e) {
            return false;
        }}
        return false;
    }
    public boolean deleteImage(Long id) {
        try {
            repo.deleteById(id);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
    public image getImage(Long id) {
        return repo.findById(id).get();
    }

    boolean typeCheck(String name) {
        String myType = name.substring(name.lastIndexOf(".")+1);
        for(String type : types) {
            if(myType.equals(type))
                return true;
        }
        System.out.printf("%s\n", myType);
        return false;
    }
}
