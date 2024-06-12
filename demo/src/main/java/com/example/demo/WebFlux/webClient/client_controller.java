package com.example.demo.WebFlux.webClient;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import javax.net.ssl.SSLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/client")
public class client_controller {

    @Autowired
    WebClient client;

    @GetMapping("/get/{param}")
    public Mono<String> test_get(@PathVariable String param) throws SSLException {
        
        return client
            .get()
            .uri("/" + param)
            .retrieve()
            .bodyToMono(String.class);
    }

    @GetMapping("/post/{param}")
    public Mono<ResponseEntity<String>> test_post(@PathVariable String param) throws SSLException {
        return client
            .post()
            .uri("")
            .bodyValue(param)
            .retrieve()
            .toEntity(String.class);
    }

    @GetMapping("/get/test/block") 
    public Mono<String> test_block() throws SSLException {
        Mono<String> mono = client
            .get()
            .uri("/testBlock")
            .retrieve()
            .bodyToMono(String.class);
        mono.block();
        return mono;

    }

    @GetMapping("/get/test/nonblock") 
    public Mono<String> test_nonblock() throws SSLException {
        Mono<String> mono = client
            .get()
            .uri("/testBlock")
            .retrieve()
            .bodyToMono(String.class).log();
        //mono.subscribe();
        return mono;
    }

    @GetMapping("get/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // Java8의 무한Stream
        return Flux.fromStream(stream.limit(10))
                .map(i -> Collections.singletonMap("value", i)).log();
    }
}