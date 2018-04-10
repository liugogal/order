package com.huatuo.order.controller;

import com.huatuo.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamSendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        streamClient.output().send(MessageBuilder.withPayload("hahahaha").build());
        return "ok";
    }
}
