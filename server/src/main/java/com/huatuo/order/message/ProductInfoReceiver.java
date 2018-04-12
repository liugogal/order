package com.huatuo.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.huatuo.order.utils.JsonUtil;
import com.huatuo.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue(value = "productInfo"))
    public void process(String jsonString) {

        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(
                jsonString,
                new TypeReference<List<ProductInfoOutput>>() {
                }
        );

        log.info("收到product发过来的数据：{}", productInfoOutputList);

        //存入redis
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {

            stringRedisTemplate.opsForValue().set(String.format(
                    PRODUCT_STOCK_TEMPLATE,
                    productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock())
            );
        }


    }


}
