package com.qf.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qf.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 为和Search模块的Controller接口进行映射
 * 寻找降级打印托底数据类
 */
@FeignClient(value = "SEARCH",fallbackFactory = SearchClientFallBackFactory.class)
public interface WebMasterFeign {

    // 访问search模块对应路径
    @RequestMapping("/search/hello")
    String search();

    // get请求路径拼接
    @GetMapping("/search/find/{id}")
    String webFind(@PathVariable(value = "id") Integer id);

    //get请求
    @GetMapping("/search/find")
    String webFind1(@RequestParam String name , String sex);

    // post请求接受参数,接受复杂对象json字符串
    @PostMapping("/search/user")
    String webJson(User user) throws JsonProcessingException ;
}
