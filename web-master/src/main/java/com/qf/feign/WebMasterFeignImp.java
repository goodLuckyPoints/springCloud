package com.qf.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qf.entity.User;
import org.springframework.stereotype.Component;

/**
 * 进行降级处理
 * 处理降级不打印错误日志只返回当前托底数据
 */
@Component
public class WebMasterFeignImp implements WebMasterFeign{

    @Override
    public String search() {
        return "hello,托底数据";
    }

    @Override
    public String webFind(Integer id) {
        return "webFind,托底数据";
    }

    @Override
    public String webFind1(String name, String sex) {
        return "webFind1,托底数据";
    }

    @Override
    public String webJson(User user) throws JsonProcessingException {
        return "webJson,托底数据";
    }
}
