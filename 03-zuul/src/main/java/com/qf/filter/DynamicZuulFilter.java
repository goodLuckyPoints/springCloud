package com.qf.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul网关动态路由
 * 获取redis缓存服务名及路径
 */
@Component
public class DynamicZuulFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取request上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        // 获取redis缓存服务名及服务路径
        String redisKey = request.getParameter("redisKey");
        String servicesId = redisTemplate.opsForValue().get("search:" +redisKey + ":serviceId");
        String requestURI = redisTemplate.opsForValue().get("search:" + redisKey + ":requestURI");

        // 执行前置过滤器过滤内容
        if (!StringUtils.isEmpty(servicesId) && !StringUtils.isEmpty(requestURI)) {
            context.put(FilterConstants.SERVICE_ID_KEY,servicesId);
            context.put(FilterConstants.REQUEST_URI_KEY,requestURI);
        }

        return null;
    }
}
