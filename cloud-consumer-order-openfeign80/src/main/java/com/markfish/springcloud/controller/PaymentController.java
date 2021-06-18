package com.markfish.springcloud.controller;

import com.markfish.springcloud.entities.CommonResult;
import com.markfish.springcloud.entities.Payment;
import com.markfish.springcloud.service.PaymentFeignService;
import feign.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentFeignService paymentFeignService;

//    @GetMapping("/consumer/payment/get/{id}")
//    public CommonResult getById(@Param("id") Long id) {
//        Payment paymentById = paymentFeignService.getPaymentById(id);
//        if(paymentById != null)
//            return new CommonResult(200, "查询成功，是", paymentById);
//        return new CommonResult(400, "没有此数据，id="+id);
//    }

    @GetMapping("/consumer/payment/get2/{id}")
    public CommonResult getById2(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }
}
