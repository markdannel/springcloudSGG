package com.markfish.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_OK(int port) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK, Service Port == " + port;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfo_Timeout(int port) {
        try{ TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e){e.printStackTrace();}
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Timeout, Service Port == " + port;
    }


    @Override
    public String paymentInfo_timeout_fallback() {
        return "timeout";
    }

    @Override
    public String paymentInfo_timeout_fallback(int a) {
        return "timeout+"+a;
    }

    /**
     * 当某段时间（时间窗口期）的请求次数失败率达到阈值，就会跳闸请求fallback方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少跳闸
    })
    @Override
    public String paymentCircuitBreaker(Long id) {
        if(id < 0) {
            throw new RuntimeException("***id 不能为负数");
        }
        return Thread.currentThread().getName()+" "+ IdUtil.simpleUUID()+" : "+id;
    }

    public String paymentCircuitBreaker_fallback(Long id) {
        return "id 不能为负数："+id;
    }
}
