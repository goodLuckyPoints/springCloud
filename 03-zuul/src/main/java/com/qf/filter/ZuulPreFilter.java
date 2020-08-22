package com.qf.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul前置过滤器
 * spring容器管理Zuul过滤器
 */
@Component
public class ZuulPreFilter extends ZuulFilter {

    //过滤类型
    @Override
    public String filterType() {
        // PRE过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 过滤类型顺序
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 执行Zuul过滤规则
    @Override
    public Object run() throws ZuulException {

        //1. 获取request上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        //2. 获取token是否一致
        String token = request.getParameter("token");

        //3. 执行前置过滤器
        if (!token.equalsIgnoreCase("123") || StringUtils.isEmpty(token)) {
            //4. token校验失败,停止继续执行
            context.setSendZuulResponse(false);
            //响应页面401
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null; //Zuul过滤无返回值响应
    }
}
