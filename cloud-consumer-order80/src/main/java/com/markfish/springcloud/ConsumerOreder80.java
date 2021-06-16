package com.markfish.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerOreder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOreder80.class, args);
    }
}
