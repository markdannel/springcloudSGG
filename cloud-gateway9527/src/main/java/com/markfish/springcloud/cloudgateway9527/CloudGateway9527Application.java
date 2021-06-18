package com.markfish.springcloud.cloudgateway9527;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGateway9527Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudGateway9527Application.class, args);
    }

}
