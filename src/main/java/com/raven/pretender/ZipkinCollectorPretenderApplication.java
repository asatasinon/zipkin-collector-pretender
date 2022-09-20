package com.raven.pretender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ZipkinCollectorPretenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinCollectorPretenderApplication.class, args);
    }

}
