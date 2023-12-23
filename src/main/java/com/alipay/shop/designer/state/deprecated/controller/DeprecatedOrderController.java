package com.alipay.shop.designer.state.deprecated.controller;

import com.alipay.shop.designer.state.deprecated.DeprecatedOrder;
import com.alipay.shop.designer.state.deprecated.service.DeprecatedOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 20:38
 */
@RestController
@RequestMapping("/deprecated/order")
public class  DeprecatedOrderController {
    @Autowired
    private DeprecatedOrderService deprecatedOrderService;


    @PostMapping("/create")
    public DeprecatedOrder createOrder(@RequestParam String productId) {
        return deprecatedOrderService.createOrder(productId);
    }

    @PostMapping("/pay")
    public DeprecatedOrder pay(@RequestParam String productId) {
        return deprecatedOrderService.pay(productId);
    }

    @PostMapping("/send")
    public DeprecatedOrder send(@RequestParam String productId) {
        return deprecatedOrderService.send(productId);
    }

    @PostMapping("/receive")
    public DeprecatedOrder receive(@RequestParam String productId) {
        return deprecatedOrderService.receive(productId);
    }
}