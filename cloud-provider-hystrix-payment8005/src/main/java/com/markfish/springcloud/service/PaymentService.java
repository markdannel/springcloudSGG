package com.markfish.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    public String paymentInfo_OK(int port);
    public String paymentInfo_Timeout(int port);
    public String paymentInfo_timeout_fallback();
    public String paymentInfo_timeout_fallback(int a);
    public String paymentCircuitBreaker(Long id);
}
