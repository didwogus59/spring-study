package com.example.demo.mySQL;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class sqlChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Lob //대용량 데이터
    private String data;

    @ManyToOne
    @JoinColumn(name = "sqlEntity_id")
    private sqlEntity parent;

    sqlChild(sqlEntity entity, String data) {
        this.parent = entity;
        this.data = data;
    }
}
