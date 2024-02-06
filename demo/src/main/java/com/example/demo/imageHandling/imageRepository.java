package com.example.demo.imageHandling;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface imageRepository extends JpaRepository<image, Long>{
    @Query("select id from image")
    List<Long> findAllId();
}
