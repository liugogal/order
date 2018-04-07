package com.huatuo.order.service.impl;

import com.huatuo.order.dataobject.OrderMaster;
import com.huatuo.order.dto.OrderDTO;
import com.huatuo.order.enums.OrderStatusEnum;
import com.huatuo.order.enums.PayStatusEnum;
import com.huatuo.order.repository.OrderDetailRepository;
import com.huatuo.order.repository.OrderMasterRepository;
import com.huatuo.order.service.OrderService;
import com.huatuo.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //TODO 查询商品信息（调用商品服务）
        //TODO 计算总价
        //TODO 扣库存（调用商品服务）
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
