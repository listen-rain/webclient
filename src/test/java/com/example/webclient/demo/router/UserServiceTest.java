package com.example.webclient.demo.router;

import com.example.webclient.demo.service.MyService;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    MyService myService;

    @Test
    void getGroups() {
    }

    @Test
    void getArrives() {

        // ...

        Mono<List<Integer>> foo = myService.someRestCall();
        Mono<List<Integer>> bar = myService.someRestCall();
        Mono<List<Integer>> baz = myService.someRestCall();

        // ..and use the results (thx to: [2] & [3]!):

        // Subscribes sequentially:

        // System.out.println("=== Flux.concat(foo, bar, baz) ===");
        // Flux.concat(foo, bar, baz).subscribe(System.out::print);

        // System.out.println("\n=== combine the value of foo then bar then baz ===");
        // foo.concatWith(bar).concatWith(baz).subscribe(System.out::print);

        // ----------------------------------------------------------------------
        // Subscribe eagerly (& simultaneously):
        System.out.println("\n=== Flux.merge(foo, bar, baz) ===");
        Flux.merge(foo, bar, baz).subscribe(System.out::print);
    }

    @Test
    void testMono() {
        Object o = userService.testMono();
        System.out.println(o);
    }

    @Test
    void testMono1() {
        userService.testMono1();
    }
}
