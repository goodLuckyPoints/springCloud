package com.qf.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 降级处理，打印服务模块抛出异常进行处理
 */
@Component
public class SearchClientFallBackFactory implements FallbackFactory<WebMasterFeign> {
    @Autowired
    private WebMasterFeignImp webMasterFeign;

    @Override
    public WebMasterFeign create(Throwable throwable) {
        //打印日志
        throwable.printStackTrace();
        return webMasterFeign;
    }
}
