package com.alipay.shop.designer.listener.controller;

import com.alipay.shop.designer.listener.service.OrderService;
import com.alipay.shop.designer.listener.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2023-12-22 22:57
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestParam String productId) {
        return orderService.create(productId);
    }

    @PostMapping("/pay")
    public Order payOrder(@RequestParam String productId) {
        return orderService.pay(productId);
    }

    @PostMapping("/send")
    public Order send(@RequestParam String productId) {
        return orderService.send(productId);
    }

    @PostMapping("/receive")
    public Order receive(@RequestParam String productId) {
        return orderService.receive(productId);
    }

}