package com.alipay.shop.designer.state;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 19:25
 */

//此处Component有问题,会导致只能创建一个订单,因此将Context转化为一个无状态的类
@Component
public class DeprecatedOrderContext {

    //新创建订单的初始状态
    @Autowired
    private DeprecatedCreateOrder deprecatedCreateOrder;

    @Autowired
    private DeprecatedPayOrder deprecatedPayOrder;

    @Autowired
    private DeprecatedSendOrder deprecatedSendOrder;

    @Autowired
    private DeprecatedReceiveOrder deprecatedReceiveOrder;

    public DeprecatedOrder createOrder(String orderId, String productId) {
        return deprecatedCreateOrder.createOrder(orderId, productId, this);
    }

    public DeprecatedOrder payOrder(String orderId) {
        return deprecatedPayOrder.payOrder(orderId, this);
    }

    public DeprecatedOrder sendOrder(String orderId) {
        return deprecatedSendOrder.sendOrder(orderId, this);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return deprecatedReceiveOrder.receiveOrder(orderId, this);
    }
}