package com.qf.controller;

import com.qf.stream.StreamOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamController {
    @Autowired
    private StreamOutput output;

    @GetMapping(value = "/send",produces = "text/html;charset=utf-8")
    public String stream() {
        /**
         * 发送消息到stream消息队列中
         */
        output.output().send(MessageBuilder.withPayload("hello world!!!").build());
        return "消息发送成功";
    }
}
