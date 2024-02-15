package com.example.demo.elasticSearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName = "my_index")
public class my_entity {
    @Id
    private String id;

	private String name;
}
