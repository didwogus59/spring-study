package com.example.demo.elasticSearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.demo.elasticSearch")
@ComponentScan(basePackages = { "com.example.demo.elasticSearch" })
@PropertySource("classpath:elasticsearch.properties")
public class elasticConfig extends ElasticsearchConfigurationSupport{
    
    @Value("${elastic_url}")
    private String host;
    
    @Bean
    public ElasticsearchClient esClient() {
        RestClient client = RestClient
        .builder(new HttpHost(host, 9200, "http"))
        .build();
        ElasticsearchTransport transport = new RestClientTransport(client, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

}
