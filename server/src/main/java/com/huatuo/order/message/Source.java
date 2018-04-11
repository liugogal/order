package com.huatuo.order.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 发送消息
 */
public interface Source {
    String OUTPUT = "output";

    String OUTPUT_BACK = "output_back";

    @Output(value = OUTPUT)
    MessageChannel output();

    @Output(value = OUTPUT_BACK)
    MessageChannel outputBack();
}