package com.markfish.springcloud.service;

import com.markfish.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-hystrix-service", fallback = PaymentFallbackService.class)
public interface PaymentFeignService {

    @GetMapping("/payment/hystrix/timeout")
    public String paymentInfo_Timeout();

    @GetMapping("/payment/hystrix/ok")
    public String paymentInfo_OK();
}
