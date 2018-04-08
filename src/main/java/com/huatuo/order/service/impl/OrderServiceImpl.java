package com.huatuo.order.service.impl;

import com.huatuo.order.client.ProductClient;
import com.huatuo.order.dataobject.OrderDetail;
import com.huatuo.order.dataobject.OrderMaster;
import com.huatuo.order.dataobject.ProductInfo;
import com.huatuo.order.dto.CartDTO;
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
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);


        //计算总价
        //根据上一步拿到的商品信息进行遍历，然后根据订单详情列表进行内部循环计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);

                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存（调用商品服务）
        //遍历订单详情列表创建cartDto数组，调用扣库存服务
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> {
                    CartDTO cartDTO = new CartDTO();
                    cartDTO.setProductId(orderDetail.getProductId());
                    cartDTO.setProductQuantity(orderDetail.getProductQuantity());
                    return cartDTO;
                })
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);


        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
