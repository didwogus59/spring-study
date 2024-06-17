package com.example.demo.WebFlux.r2dbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("TEST")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class oracle_test {
    @Column("DATA") private String data;
    @Column("TITLE") private String title;
    @Column("ID") @Id private int id;
    
}
