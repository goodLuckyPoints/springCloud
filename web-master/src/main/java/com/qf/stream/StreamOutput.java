package com.qf.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * stream生产者rabbitMQ声明交换机名称
 */
public interface StreamOutput {

    @Output("stream")
    MessageChannel output();
}
