package com.example.webclient.demo.controller;

import com.example.webclient.demo.router.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping("/test1")
    public Object test1() {
        return userService.testMono1();
    }

    @GetMapping("/test2")
    public Mono<Map<String, Object>> test2() {
        return userService.testMono2();
    }

    @GetMapping("/test3")
    public Mono<Map<String, Object>> test3() {
        return userService.testMono3();
    }

    @GetMapping("/test4")
    public Object test4() {
        return userService.testMono4();
    }
}
