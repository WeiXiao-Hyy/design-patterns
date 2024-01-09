package com.alipay.shop.designer.listener.service;

import com.alipay.shop.designer.command.OrderCommandInvoker;
import com.alipay.shop.designer.command.cmd.OrderCommand;
import com.alipay.shop.designer.decorator.OrderServiceInterface;
import com.alipay.shop.designer.facade.PayFacade;
import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.designer.listener.OrderState;
import static com.alipay.shop.designer.listener.OrderState.ORDER_WAIT_PAY;
import com.alipay.shop.designer.listener.OrderStateChangeAction;
import com.alipay.shop.designer.mediator.AbstractCustomer;
import com.alipay.shop.designer.mediator.Buyer;
import com.alipay.shop.designer.mediator.Mediator;
import com.alipay.shop.designer.mediator.Payer;
import com.alipay.shop.util.RedisCommonProcessor;
import java.util.HashMap;
import java.util.Map;
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
public class OrderService implements OrderServiceInterface {

    //order状态机
    @Resource
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;

    //order状态机持久化
    @Resource
    private RedisStateMachinePersister<OrderState, OrderStateChangeAction> stateMachineRedisPersister;

    @Resource
    private RedisCommonProcessor redisProcessor;

    @Resource
    private OrderCommand orderCommand;

    @Resource
    private PayFacade payFacade;

    @Resource
    private Mediator mediator;

    public Order create(String productId) {
        String orderId = "OID" + productId;
        Order order = Order.builder()
                .orderId(orderId)
                .productId(productId)
                .orderState(ORDER_WAIT_PAY)
                .build();
        //将新订单存入Redis缓存里面,15分钟后失效
        redisProcessor.set(orderId, order, 900);

        //使用命令模式
        OrderCommandInvoker invoker = new OrderCommandInvoker();
        invoker.invoke(orderCommand, order);

        return order;
    }

    //TODO: 待完善的PAY操作
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

    public String getUrl(String productId, Float price, Integer payType) {
        String orderId = "OID" + productId;
        Order order = (Order) redisProcessor.get(orderId);
        order.setPrice(price);
        return payFacade.pay(order, payType);
    }

    public void friendPay(String sourceCustomer, String orderId, String targetCustomer, String payResult, String role) {
        //创建中介者
        Buyer buyer = new Buyer(mediator, orderId, sourceCustomer);
        Payer payer = new Payer(mediator, orderId, sourceCustomer);
        Map<String, AbstractCustomer> map = new HashMap<>();
        map.put("buyer", buyer);
        map.put("payer", payer);
        mediator.customerInstances.put(orderId, map);
        if ("B".equals(role)) {
            buyer.messageTransfer(orderId, targetCustomer, payResult);
        } else if ("P".equals(role)) {
            payer.messageTransfer(orderId, targetCustomer, payResult);
        }
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