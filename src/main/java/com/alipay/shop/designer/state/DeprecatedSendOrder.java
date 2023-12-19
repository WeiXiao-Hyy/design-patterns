package com.alipay.shop.designer.state;

import com.alipay.shop.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 19:23
 */
@Component
public class DeprecatedSendOrder extends DeprecatedAbstractOrderState {

    @Autowired
    private RedisCommonProcessor redisClient;

    @Autowired
    private DeprecatedReceiveOrder deprecatedReceiveOrder;

    @Override
    protected DeprecatedOrder sendOrder(String orderId, DeprecatedOrderContext context) {
        DeprecatedOrder order = (DeprecatedOrder) redisClient.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_SEND)) {
            throw new UnsupportedOperationException("Order state should be ORDER_WAIT_SEND, not now it's state is : " + order.getState());
        }
        order.setState(ORDER_WAIT_RECEIVE);
        redisClient.set(orderId, order);
        //TODO: 观察者模式，发送订单发货Event
        context.setCurrentStatus(this.deprecatedReceiveOrder);
        return order;
    }
}