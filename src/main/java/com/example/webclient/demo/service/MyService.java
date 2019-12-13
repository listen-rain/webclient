package com.example.webclient.demo.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MyService {

    private final WebClient webClient;

    // the reference doc seems incorrect saying "public MyBean(" :)
    public MyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<List<Integer>> someRestCall() {
        return this.webClient.get().uri("http://localhost:20090/supervise/test/mono2")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Integer>>() {});
    }
}
