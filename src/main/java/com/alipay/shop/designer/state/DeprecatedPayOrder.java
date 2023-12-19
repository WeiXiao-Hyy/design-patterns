package com.alipay.shop.designer.state;

import com.alipay.shop.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 19:24
 */

@Component
public class DeprecatedPayOrder extends DeprecatedAbstractOrderState {

    @Autowired
    private RedisCommonProcessor redisClient;

    @Override
    protected DeprecatedOrder payOrder(String orderId, DeprecatedOrderContext context) {
        DeprecatedOrder order = (DeprecatedOrder) redisClient.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_PAY)) {
            throw new UnsupportedOperationException("Order state should be ORDER_WAIT_PAY, not now it's state is : " + order.getState());
        }

        //TODO: 支付逻辑
        order.setState(ORDER_WAIT_SEND);
        redisClient.set(orderId, order);
        //TODO:观察者模式
        super.notifyObserver(orderId, ORDER_WAIT_SEND);
        return order;
    }
}