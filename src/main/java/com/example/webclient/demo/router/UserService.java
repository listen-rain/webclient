package com.example.webclient.demo.router;

import com.example.webclient.demo.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Service
public class UserService {
    @Autowired
    private WebClientService webClientService;

    public Mono<String> getMono(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            name = String.valueOf(System.currentTimeMillis());
        }

        return webClientService.webBuilder().get()
                .uri("/test/ok?name={name}", name)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Map<String, String>> getMono1() {
        return webClientService.webBuilder().get()
                .uri("/test/mono1")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {});
    }

    public Mono<List<Integer>> getMono2() {
        return webClientService.webBuilder().post()
                .uri("/test/mono2")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Integer>>() {});
    }

    public Object testMono() {
        Mono<Map<String, String>> mapMono = this.getMono1();
        Mono<List<Integer>> listMono = this.getMono2();

        Map<String, Object> map1 = new HashMap<>();
        try {
            Map<String, Object> data = Mono.zip(mapMono, listMono, (map, list) -> {
                map1.put("map", map);
                map1.put("list", list);

                return map1;
            }).block();

            System.out.println(map1);
            System.out.println(data);

            return data;
        } catch (Exception e) {
            log.error("webClient 请求失败：{}", e.getMessage());
        }

        return map1;
    }

    public Object testMono1() {
        Mono<Map<String, String>> mapMono = this.getMono1();
        Mono<List<Integer>> listMono = this.getMono2();

        return Flux.zip(mapMono, listMono);
    }

    public Mono<Map<String, Object>> testMono2() {
        Mono<Map<String, String>> mapMono = this.getMono1();
        Mono<List<Integer>> listMono = this.getMono2();
        Map<String, Object> map1 = new HashMap<>();

        return Mono.zip(mapMono, listMono, (map, list) -> {
            map1.put("map", map);
            map1.put("list", list);

            return map1;
        });
    }

    public Mono<Map<String, Object>> testMono3() {
        List<Integer> list1 = new ArrayList<>();
        Mono<Map<String, String>> mapMono = this.getMono1().flatMap(map -> {
            System.out.println(map);
            list1.add(1234);

            return Mono.justOrEmpty(map);
        });

        List<Integer> list2 = new ArrayList<>();
        Mono<List<Integer>> listMono = this.getMono2().flatMap(list -> {
            System.out.println(list);
            list2.add(5678);
            return Mono.justOrEmpty(list);
        });

        Map<String, Object> map1 = new HashMap<>();
        return Mono.zip(mapMono, listMono, (map, list) -> {
            map1.put("map", map);
            map1.put("list", list);
            map1.put("list1", list1);
            map1.put("list2", list2);

            System.out.println(map1);
            return map1;
        });
    }

    public Mono<Object> testMono4() {
        long startTime = System.currentTimeMillis();
        Mono<List<Integer>> result = this.getMono2();
        Mono<Map<String, String>> mapMono2 = this.getMono1();

        Map<String, Object> map = new HashMap<>();
        return Mono.zip(result, mapMono2).flatMap(list -> {
            map.put("list", list);
            System.out.println(map);

            List<Integer> userIds = list.getT1();
            Mono<String> mapMono = this.getMono(StringUtils.join(userIds, ","));
            return mapMono.flatMap(s -> {
                map.put("map", s);
                System.out.println(map);

                log.info("useTIme: {}", System.currentTimeMillis() - startTime);
                return Mono.justOrEmpty(map);
            });
        });
    }
}
