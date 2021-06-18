package com.markfish.springcloud.controller;

import com.markfish.springcloud.entities.CommonResult;
import com.markfish.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "global_error_page")
public class PaymentController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/hystrix/timeout")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentInfo_Timeout(){
        return paymentFeignService.paymentInfo_Timeout();
    }

    @GetMapping("/consumer/payment/hystrix/timeout2")
    //使用默认@DefaultProperties配置的回调函数
    @HystrixCommand
    public String paymentInfo_Timeout2(){
        return paymentFeignService.paymentInfo_Timeout();
    }

    public String paymentInfo_timeout_fallback() {
        return "timeout o(╥﹏╥)o";
    }

    public String global_error_page() {
        return "global error page  o(╥﹏╥)o";
    }

    @GetMapping("/consumer/payment/hystrix/ok")
    public String paymentInfo_OK() {
        return paymentFeignService.paymentInfo_OK();
    }


}
