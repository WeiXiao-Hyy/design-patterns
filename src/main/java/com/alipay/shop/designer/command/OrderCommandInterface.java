package com.alipay.shop.designer.command;

import com.alipay.shop.designer.listener.Order;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2023/12/23 11:18
 * @see com.alipay.shop.designer.command
 */
public interface OrderCommandInterface {
    //执行命令
    void execute(Order order);
}
