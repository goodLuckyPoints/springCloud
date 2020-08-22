package com.qf.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * stream消费rabbitMQ消费队列信息
 * 指定消息队列名称
 */
public interface StreamInput {

    @Input("stream")
    //subscribe模型消息消费队列管道
    SubscribableChannel input();
}
