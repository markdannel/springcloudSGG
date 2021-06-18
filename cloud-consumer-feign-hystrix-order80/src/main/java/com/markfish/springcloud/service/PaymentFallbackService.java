package com.markfish.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public String paymentInfo_Timeout() {
        return "feign service -PaymentFallbackService- 统一处理error ";
    }

    @Override
    public String paymentInfo_OK() {
        return "feign service -PaymentFallbackService- 统一处理error ";
    }
}
