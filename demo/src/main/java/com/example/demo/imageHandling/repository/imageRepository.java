package com.example.demo.imageHandling.repository;

import com.example.demo.imageHandling.entity.image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface imageRepository extends JpaRepository<image, Long>{
    
}
