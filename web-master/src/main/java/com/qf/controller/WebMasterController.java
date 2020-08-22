package com.qf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qf.entity.User;
import com.qf.feign.WebMasterFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class WebMasterController {
    @Resource
    private EurekaClient eurekaClient;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private WebMasterFeign webMasterFeign;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/world")
    public String webMaster() {
        //Eureka上传的ip地址信息名字
//      //客户端上传的ip是不安全的
//        InstanceInfo eureka = eurekaClient.getNextServerFromEureka("SEARCH", false);
//        String ipAddr = eureka.getIPAddr();
//        int port = eureka.getPort();
//        //访问Eureka服务端上传ip地址信息
//        String result = restTemplate.getForObject("http://" + ipAddr + ":" + port + "/search/hello", String.class);
//        String result = restTemplate.getForObject("http://SEARCH/search/hello", String.class);
        String result = webMasterFeign.search();
        return result;
    }

    // get请求路径拼接
    @GetMapping("/find/{id}")
    public String webFind(@PathVariable(value = "id") Integer id) {
        System.out.println("接受到参数："+id);
        webMasterFeign.webFind(id);
        return "接受到参数："+id;
    }

    //get请求,降级Fallback不打印错误信息，则直接抛出托底数据
    @GetMapping("/find")
    // hystrix调用降级方法处理，处理自身服务器报错寻找降级，
    // 调用提供方服务器报错找降级服务返回托底数据
    @HystrixCommand(fallbackMethod = "webFindFallback")
    public String webFind1(@RequestParam String name ,String sex) {
        System.out.println("姓名："+name+"性别："+sex);
        int i = 1/0;
        return webMasterFeign.webFind1(name, sex);
    }
    public String webFindFallback(@RequestParam String name ,String sex) {
        return "爆炸";
    }

    // post请求接受参数,接受复杂对象json字符串
    @PostMapping("/user")
    public String webJson(User user) throws JsonProcessingException {
        System.out.println(user);
        //将对象转换为json字符串并显示
        return objectMapper.writeValueAsString(user);
    }
}
