package com.example.demo.WebFlux.r2dbc;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface oracle_repository extends R2dbcRepository<oracle_test, Integer> {
    
}
