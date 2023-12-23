package com.alipay.shop.designer.command;

import com.alipay.shop.designer.listener.Order;

/**
 * @author hyy
 * @Description
 * @create 2023-12-23 11:22
 */
public class OrderCommandInvoker {
    public void invoke(OrderCommandInterface command, Order order) {
        //调用命令角色的execute方法
        command.execute(order);
    }
}