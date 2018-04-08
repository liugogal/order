package com.huatuo.order.controller;

import com.huatuo.order.client.ProductClient;
import com.huatuo.order.dataobject.ProductInfo;
import com.huatuo.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping(value = "/clientmsg")
    public String msg() {

        String response = productClient.productMsg();
        return response;
    }

    @GetMapping(value = "/getProductList")
    public String getProductList() {
        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}", productInfos);
        return "ok";
    }

    @GetMapping(value = "/productdecreaseStock")
    public String productdecreaseStock() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId("164103465734242707");
        cartDTO.setProductQuantity(3);
        productClient.decreaseStock(Arrays.asList(cartDTO));
        return "ok";
    }

}
