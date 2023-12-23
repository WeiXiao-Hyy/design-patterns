package com.alipay.shop.designer.command.cmd;

import com.alipay.shop.designer.command.OrderCommandInterface;
import com.alipay.shop.designer.command.OrderCommandReceiver;
import com.alipay.shop.designer.listener.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-23 11:21
 */
@Component
public class OrderCommand implements OrderCommandInterface {

    @Autowired
    private OrderCommandReceiver receiver;

    @Override
    public void execute(Order order) {
        this.receiver.action(order);
    }
}