package com.example.demo.webClient;

import javax.net.ssl.SSLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@RestController
public class client_controller {

    @Autowired
    WebClient client;

    @GetMapping("/client/get/{param}")
    public Mono<String> test_get(@PathVariable String param) throws SSLException {
        return client
            .get()
            .uri("/get/" + param)
            .retrieve()
            .bodyToMono(String.class);
    }

    @GetMapping("/client/post/{param}")
    public Mono<String> test_post(@PathVariable String param) throws SSLException {
        return client
            .post()
            .uri("/post/" + param)
            .retrieve()
            .bodyToMono(String.class);
    }
}