package com.huatuo.order.controller;

import com.huatuo.product.client.ProductClient;
import com.huatuo.product.common.DecreaseStockInput;
import com.huatuo.product.common.ProductInfoOutput;
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

//        String response = productClient.productMsg();
        return "";
    }

    @GetMapping(value = "/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfos = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}", productInfos);
        return "ok";
    }

    @GetMapping(value = "/productdecreaseStock")
    public String productdecreaseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
        decreaseStockInput.setProductId("164103465734242707");
        decreaseStockInput.setProductQuantity(3);
        productClient.decreaseStock(Arrays.asList(decreaseStockInput));
        return "ok";
    }

}
