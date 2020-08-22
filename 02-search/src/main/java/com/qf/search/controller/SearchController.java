package com.qf.search.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@RefreshScope
public class SearchController {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${server.port}")
    private int port;
    @Value("${version}")
    private String version;

    @RequestMapping("/hello")
    public String search() {
        System.out.println("hello search");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello"+port+version;
    }
    // get请求路径拼接
    @GetMapping("/find/{id}")
    public String webFind(@PathVariable(value = "id") Integer id) {
        System.out.println("接受到参数："+id);
//        int i =1/0;
        return "接受到参数："+id;
    }

    //get请求
    @GetMapping("/find")
    public String webFind1(@RequestParam String name , String sex) {
        System.out.println("姓名："+name+"性别："+sex);
//        int i =1/0;
        return "姓名："+name+"性别："+sex;
    }

    // post请求接受参数,接受复杂对象json字符串
    @PostMapping("/user")
    public String webJson(User user) throws JsonProcessingException {
        System.out.println(user);
        //将对象转换为json字符串并显示
        return objectMapper.writeValueAsString(user);
    }
}
