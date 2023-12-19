package com.alipay.shop.designer.state;


import lombok.Setter;
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

    //引入抽象状态，用于状态方法的调用
    @Setter
    private DeprecatedAbstractOrderState currentStatus;

    //新创建订单的初始状态
    @Autowired
    private DeprecatedCreateOrder deprecatedCreateOrder;

    public DeprecatedOrder createOrder(String orderId, String productId) {
        this.currentStatus = this.deprecatedCreateOrder;
        return currentStatus.createOrder(orderId, productId, this);
    }


    public DeprecatedOrder payOrder(String orderId) {
        return currentStatus.payOrder(orderId, this);
    }

    public DeprecatedOrder sendOrder(String orderId) {
        return currentStatus.sendOrder(orderId, this);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return currentStatus.receiveOrder(orderId, this);
    }
}