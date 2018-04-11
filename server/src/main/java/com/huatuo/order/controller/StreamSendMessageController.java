package com.huatuo.order.controller;

import com.huatuo.order.dto.OrderDTO;
import com.huatuo.order.message.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class StreamSendMessageController {

    @Autowired
    private Source source;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("12321312");
        source.output().send(MessageBuilder.withPayload(orderDTO).build());
        return "ok";
    }
}
