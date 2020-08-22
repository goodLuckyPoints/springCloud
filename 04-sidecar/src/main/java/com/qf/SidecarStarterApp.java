package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * 配置跨平台服务路径Sidecar
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSidecar
public class SidecarStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(SidecarStarterApp.class,args);
    }
}
