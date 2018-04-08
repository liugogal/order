package com.huatuo.order.dto;

import lombok.Data;

@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品库存
     */
    private Integer productQuantity;

}
