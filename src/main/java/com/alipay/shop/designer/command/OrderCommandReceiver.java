package com.alipay.shop.designer.command;

import com.alipay.shop.designer.listener.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-23 11:17
 */
@Component
@Slf4j
public class OrderCommandReceiver {
    //接收到命令后执行
    public void action(Order order) {
        switch (order.getOrderState()) {
            case ORDER_WAIT_PAY:
                log.info("创建订单: order={}, 存入DB", order);
                return;
            case ORDER_WAIT_SEND:
                log.info("支付订单: order={}", order);
                log.info("通过mq通知财务部门");
                log.info("通过mq通知物流部门");
            case ORDER_WAIT_RECEIVE:
                log.info("订单发货: order={}", order);
            case ORDER_FINISH:
                log.info("订单完成, order={}", order);
            default:
                throw new UnsupportedOperationException("Order State error");
        }
    }
}