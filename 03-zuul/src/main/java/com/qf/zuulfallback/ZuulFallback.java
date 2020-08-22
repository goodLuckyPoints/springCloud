package com.qf.zuulfallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 访问监听动态路由服务，访问超时会走降级托底数据
 * Zuul降级机制
 */
@Component
public class ZuulFallback implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 执行zuul降级处理，及返回托底数据
     * 访问路由服务及路径，超时时走托底数据
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.println("超时托底数据，降级服务："+route+","+cause.getMessage());
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // 指定具体HttpStatus
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 状态码
                return HttpStatus.INSUFFICIENT_STORAGE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                //指定状态码错误信息断路语
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String msg = "当前服务" + route + "出现问题";
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_HTML);
                return headers;
            }
        };
    }
}
