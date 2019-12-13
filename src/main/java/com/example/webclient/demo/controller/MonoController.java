package com.example.webclient.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class MonoController {
    @GetMapping("/ok")
    public String testOk(@RequestParam String name) {
        return "Ok-" + name;
    }

    @GetMapping("/mono1")
    public Map<String, String> mono1() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        Map<String, String> map = new HashMap<>();
        map.put("t1", "test-1");
        map.put("t2", "test-2");

        return map;
    }

    @PostMapping("/mono2")
    public List<Integer> mono2() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        return Arrays.asList(123, 456);
    }
}
