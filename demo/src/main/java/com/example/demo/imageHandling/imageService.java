package com.example.demo.imageHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.imageHandling.entity.image;
import com.example.demo.imageHandling.repository.imageRepository;

import jakarta.annotation.Resource;

@Service
public class imageService {
    
    @Autowired
    private imageRepository repo;

    // @Resource
    // private JdbcTemplate imageTemplate;

    // @Autowired
    // private JdbcTemplate template;

    public Long saveImage(MultipartFile file) {
        image image = new image();
        image.setName(file.getOriginalFilename());
        try {
            image.setData(file.getBytes());
        } catch(Exception e) {
            
        }
        return repo.save(image).getId();
    }

    public image getImage(Long id) {
        return repo.findById(id).get();
    }

}
