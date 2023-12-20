package com.alipay.shop.designer.state.deprecated;

import com.alipay.shop.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 19:25
 */
@Component
public class DeprecatedReceiveOrder extends DeprecatedAbstractOrderState {

    @Autowired
    private RedisCommonProcessor redisClient;

    @Override
    protected DeprecatedOrder receiveOrder(String orderId, DeprecatedOrderContext context) {
        DeprecatedOrder order = (DeprecatedOrder) redisClient.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_RECEIVE)) {
            throw new UnsupportedOperationException("Order state should be ORDER_WAIT_RECEIVE, not now it's state is : " + order.getState());
        }
        order.setState(ORDER_FINISH);
        redisClient.remove(orderId);
        //TODO:观察者模式
        super.notifyObserver(orderId, ORDER_FINISH);
        return order;
    }
}