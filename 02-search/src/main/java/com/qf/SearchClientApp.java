package com.qf;

import com.qf.stream.StreamInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(value = StreamInput.class)
public class SearchClientApp {

    public static void main(String[] args) {
        SpringApplication.run(SearchClientApp.class,args);
    }


}
