package com.qf;

import com.qf.stream.StreamOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * Eureka客户端往EurekaServer上传ip地址信息
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
// 扫描Servlet访问路径注解
@ServletComponentScan(basePackages = "com.qf.servlet")
@EnableBinding(value = StreamOutput.class)
public class WebMasterApp {

    public static void main(String[] args) {
        SpringApplication.run(WebMasterApp.class,args);
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
    }
    //访问其他模块

    @Bean
    //整合Ribbon
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
