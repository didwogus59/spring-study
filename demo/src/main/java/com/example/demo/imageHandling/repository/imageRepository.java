package com.example.demo.imageHandling.repository;

import com.example.demo.imageHandling.entity.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface imageRepository extends JpaRepository<image, Long>{
    @Query("select id from image")
    List<Long> findAllId();
}
