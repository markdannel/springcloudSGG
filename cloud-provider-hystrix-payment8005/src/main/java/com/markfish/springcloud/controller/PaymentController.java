package com.markfish.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.markfish.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private int servicePort;

    @GetMapping("/payment/hystrix/timeout")
    public String paymentInfo_Timeout() {
        return paymentService.paymentInfo_Timeout(this.servicePort);
    }

    @GetMapping("/payment/hystrix/ok")
    public String paymentInfo_OK() {
        return paymentService.paymentInfo_OK(this.servicePort);
    }

    @GetMapping("payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        return paymentService.paymentCircuitBreaker(id);
    }

}
