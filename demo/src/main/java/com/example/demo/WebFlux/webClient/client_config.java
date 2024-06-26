package com.example.demo.WebFlux.webClient;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
public class client_config {
    
    @Bean
    public WebClient client() throws SSLException
    {
        SslContext sslContext;
        sslContext = SslContextBuilder
            .forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build();

        // HttpClient httpClient = HttpClient
        //     .create()
        //     .secure(t ->
        //             t.sslContext(sslContext));
        HttpClient httpClient = HttpClient.create();

        WebClient client = WebClient
            .builder()
            .baseUrl("http://localhost:3000")
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
            return client;
    }
}
