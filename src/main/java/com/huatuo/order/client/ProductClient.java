package com.huatuo.order.client;


import com.huatuo.order.dataobject.ProductInfo;
import com.huatuo.order.dto.CartDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping(value = "/msg")
    String productMsg();

    @PostMapping(value = "/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping(value = "/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);

}
