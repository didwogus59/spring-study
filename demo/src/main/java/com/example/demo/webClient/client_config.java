package com.example.demo.webClient;

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

        HttpClient httpClient = HttpClient
            .create()
            .secure(t ->
                    t.sslContext(sslContext));

        WebClient client = WebClient
            .builder()
            .baseUrl("https://localhost:5000/webclient")
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
            return client;
    }
}
