package com.alipay.shop.designer.listener.service;

import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.designer.listener.OrderState;
import static com.alipay.shop.designer.listener.OrderState.ORDER_WAIT_PAY;
import com.alipay.shop.designer.listener.OrderStateChangeAction;
import com.alipay.shop.util.RedisCommonProcessor;
import javax.annotation.Resource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.redis.RedisStateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2023-12-22 22:58
 */
@Service
public class OrderService {

    //order状态机
    @Resource
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;

    //order状态机持久化
    @Resource
    private RedisStateMachinePersister<OrderState, OrderStateChangeAction> stateMachineRedisPersister;

    @Resource
    private RedisCommonProcessor redisProcessor;

    public Order create(String productId) {
        String orderId = "OID" + productId;
        Order order = Order.builder()
                .orderId(orderId)
                .productId(productId)
                .orderState(ORDER_WAIT_PAY)
                .build();
        //将新订单存入Redis缓存里面,15分钟后失效
        redisProcessor.set(orderId, order, 900);
        return order;
    }

    //待完善的PAY操作
    public Order pay(String productId) {
        String orderId = "OID" + productId;
        Order order = (Order) redisProcessor.get(orderId);
        //包装订单状态Message, 并附带订单操作PAY_ORDER
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.PAY_ORDER)
                .setHeader("order", order)
                .build();
        //将Message传递给Spring状态机
        if (changeStateAction(message, order)) {
            return order;
        }
        return null;
    }

    public Order send(String productId) {
        String orderId = "OID" + productId;
        Order order = (Order) redisProcessor.get(orderId);
        //包装订单状态Message, 并附带订单操作PAY_ORDER
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.SEND_ORDER)
                .setHeader("order", order)
                .build();
        //将Message传递给Spring状态机
        if (changeStateAction(message, order)) {
            return order;
        }
        return null;
    }

    public Order receive(String productId) {
        String orderId = "OID" + productId;
        Order order = (Order) redisProcessor.get(orderId);
        //包装订单状态Message, 并附带订单操作PAY_ORDER
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.RECEIVE_ORDER)
                .setHeader("order", order)
                .build();
        //将Message传递给Spring状态机
        if (changeStateAction(message, order)) {
            return order;
        }
        return null;
    }

    private boolean changeStateAction(Message<OrderStateChangeAction> message, Order order) {
        boolean res = false;
        try {
            orderStateMachine.start();
            //从Redis缓存中读取状态机
            stateMachineRedisPersister.restore(orderStateMachine, order.getOrderId() + "STATE");
            //将message发送给OrderStateListener
            res = orderStateMachine.sendEvent(message);
            //将更改完订单状态的状态机存储到Redis缓存中
            stateMachineRedisPersister.persist(orderStateMachine, order.getOrderId() + "STATE");

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return res;
    }
}