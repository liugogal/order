package com.huatuo.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage() {
        amqpTemplate.convertAndSend("myQueue", "taotao");
    }

    @Test
    public void sendComputer() {
        amqpTemplate.convertAndSend("myOrder","computer","哈哈电脑");
    }


}
