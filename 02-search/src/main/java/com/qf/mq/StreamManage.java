package com.qf.mq;

import com.qf.stream.StreamInput;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Component
public class StreamManage {

    /**
     * 监听队列信息名称,消费队列信息内容
     * @return
     */
    @StreamListener("stream")
    @GetMapping("/input")
    public void input(String msg, @Header(name = AmqpHeaders.CHANNEL) Channel channel,@Header(name = AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        System.out.println("消费队列信息：" + msg);
        channel.basicAck(deliveryTag,false);
    }
}
