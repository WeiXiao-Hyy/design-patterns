package com.alipay.shop.designer.strategy;

import com.alipay.shop.designer.listener.Order;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2023/12/25 16:20
 * @see com.alipay.shop.designer.strategy
 */
public interface PayStrategyInterface {
    String pay(Order order);
}
