package com.example.demo.imageHandling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class image {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    @Lob
    @Column(length = 16777215) 
    private byte[] data = null;
}
