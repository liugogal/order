package com.huatuo.order.controller;

import com.huatuo.order.converter.OrderForm2OrderDTOConverter;
import com.huatuo.order.dto.OrderDTO;
import com.huatuo.order.enums.ResultEnum;
import com.huatuo.order.exception.OrderException;
import com.huatuo.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {

    @PostMapping(value = "/create")
    public void create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        //处理表单错误
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        //OrderForm -> OrderDTO

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {

        }
    }

}
