package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
@ServletComponentScan("com.qf.filter")
public class ConfigStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigStarterApp.class,args);
    }
}
