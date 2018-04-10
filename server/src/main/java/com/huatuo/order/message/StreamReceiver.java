package com.huatuo.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class StreamReceiver {

    @StreamListener(Sink.INPUT)
    public void listener(Object message) {
        log.info("StreamReceiver: {}", message);
    }

}
