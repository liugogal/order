package com.huatuo.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveMessage {

    //1、@RabbitListener(queues = "myQueue") 用于在消息队列中已有的队列，需要手动创建
    //2、@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3、使用bindings
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myExchange"),
            key = "key",
            value = @Queue("myQueue")
    ))
    public void receive(String message) {
        log.info("ReceiveMessage: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerQueue")
    ))
    public void computerReceive(String message) {
        log.info("computer ReceiveMessage: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitQueue")
    ))
    public void fruitReceive(String message) {
        log.info("fruit ReceiveMessage: {}", message);
    }


}
