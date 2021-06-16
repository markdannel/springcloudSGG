package com.markfish.springcloud.controller;

import com.markfish.springcloud.entities.CommonResult;
import com.markfish.springcloud.entities.Payment;
import com.markfish.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**插入结果："+result);
        if(result>0)
            return new CommonResult(200, "插入成功, 端口号=="+serverPort, result);
        return new CommonResult(444, "插入失败", null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**查询结果："+payment);
        if (payment != null)
            return new CommonResult(200, "查询成功，呵呵, 端口号=="+serverPort, payment);
        return new CommonResult(444, "没有对应记录，查询失败，ID="+id, null);
    }
}
