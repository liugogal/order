package com.huatuo.order.message;

import com.huatuo.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class StreamReceiver {

    @StreamListener(Sink.INPUT)
    @SendTo(Sink.INPUT_BACK)
    public String listener(OrderDTO message) {
        log.info("StreamReceiver: {}", message);

        return "消息收到咯...";
    }

    @StreamListener(Sink.INPUT_BACK)
    public void listener2(Object message) {
        log.info("StreamReceiver: {}", message);
    }

}
