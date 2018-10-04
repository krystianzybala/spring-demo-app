package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@SpringBootApplication
@EnableWebFlux
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
class Number {
    private long value;
}


@RestController
@RequestMapping(path ="/api/v1/numbers")
class ExampleController {
    Flux<Number> getRandomValue() {
        final long random = new Random().nextLong();

        return Flux
                .interval(Duration.ofSeconds(1))
                .map(l -> l * random)
                .map(Number::new);
    }
}