package com.huatuo.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    @Input(value = "myMessage")
    SubscribableChannel input();


    @Output(value = "myMessage")
    MessageChannel output();
}
