package com.alipay.shop.designer.listener.listener;

import com.alipay.shop.designer.command.OrderCommandInvoker;
import com.alipay.shop.designer.command.cmd.OrderCommand;
import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.designer.listener.OrderState;
import com.alipay.shop.designer.listener.OrderStateChangeAction;
import com.alipay.shop.util.RedisCommonProcessor;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2023-12-22 22:46
 */
@Service
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListener {

    @Resource
    private RedisCommonProcessor redisProcessor;

    @Resource
    private OrderCommand orderCommand;

    @OnTransition(source = "ORDER_WAIT_PAY", target = "ORDER_WAIT_SEND")
    public boolean payToSend(Message<OrderStateChangeAction> message) {
        Order order = (Order) message.getHeaders().get("order");

        if (OrderState.ORDER_WAIT_PAY != Optional.ofNullable(order).map(Order::getOrderState).orElse(null)) {
            throw new UnsupportedOperationException("Order state error!");
        }
        order.setOrderState(OrderState.ORDER_WAIT_SEND);
        redisProcessor.set(order.getOrderId(), order);

        //使用命令模式
        OrderCommandInvoker invoker = new OrderCommandInvoker();
        invoker.invoke(orderCommand, order);
        return true;
    }

    @OnTransition(source = "ORDER_WAIT_SEND", target = "ORDER_WAIT_RECEIVE")
    public boolean sendToReceive(Message<OrderStateChangeAction> message) {
        Order order = (Order) message.getHeaders().get("order");

        if (OrderState.ORDER_WAIT_SEND != Optional.ofNullable(order).map(Order::getOrderState).orElse(null)) {
            throw new UnsupportedOperationException("Order state error!");
        }
        order.setOrderState(OrderState.ORDER_WAIT_RECEIVE);
        redisProcessor.set(order.getOrderId(), order);

        //使用命令模式
        OrderCommandInvoker invoker = new OrderCommandInvoker();
        invoker.invoke(orderCommand, order);
        return true;
    }

    @OnTransition(source = "ORDER_WAIT_RECEIVE", target = "ORDER_FINISH")
    public boolean receiveToFinish(Message<OrderStateChangeAction> message) {
        Order order = (Order) message.getHeaders().get("order");

        if (OrderState.ORDER_WAIT_RECEIVE != Optional.ofNullable(order).map(Order::getOrderState).orElse(null)) {
            throw new UnsupportedOperationException("Order state error!");
        }
        order.setOrderState(OrderState.ORDER_FINISH);
        redisProcessor.remove(order.getOrderId());
        redisProcessor.remove(order.getOrderId() + "STATE");

        //使用命令模式
        OrderCommandInvoker invoker = new OrderCommandInvoker();
        invoker.invoke(orderCommand, order);
        return true;
    }
}