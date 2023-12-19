package com.alipay.shop.designer.state.service;

import com.alipay.shop.designer.state.DeprecatedOrder;
import com.alipay.shop.designer.state.DeprecatedOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 20:39
 */
@Service
public class DeprecatedOrderService {

    @Autowired
    private DeprecatedOrderContext context;

    public DeprecatedOrder createOrder(String productId) {
        String orderId = "OID" + productId;
        return context.createOrder(orderId, productId);
    }

    public DeprecatedOrder pay(String productId) {
        String orderId = "OID" + productId;
        return context.payOrder(orderId);
    }

    public DeprecatedOrder send(String productId) {
        String orderId = "OID" + productId;
        return context.sendOrder(orderId);
    }


    public DeprecatedOrder receive(String productId) {
        String orderId = "OID" + productId;
        return context.receiveOrder(orderId);
    }
}