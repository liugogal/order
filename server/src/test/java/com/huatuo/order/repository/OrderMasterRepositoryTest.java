package com.huatuo.order.repository;

import com.huatuo.order.OrderApplicationTests;
import com.huatuo.order.dataobject.OrderMaster;
import com.huatuo.order.enums.OrderStatusEnum;
import com.huatuo.order.enums.PayStatusEnum;
import com.huatuo.order.repository.OrderMasterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() throws Exception {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12345678");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("13648082878");
        orderMaster.setBuyerAddress("成都金牛区");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);

    }
}