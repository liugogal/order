package com.huatuo.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


/**
 * 接受消息
 */
public interface Sink {
    String INPUT = "input";
    String INPUT_BACK = "input_back";

    @Input(value = INPUT)
    SubscribableChannel input();
    @Input(value = INPUT_BACK)
    SubscribableChannel inputBack();
}