package com.alipay.shop.designer.strategy;

import com.alipay.shop.designer.listener.Order;

/**
 * @author hyy
 * @Description
 * @create 2023-12-27 23:23
 */
public class PayContext {

    private final PayStrategyInterface payStrategy;

    public PayContext(PayStrategyInterface payStrategy) {
        this.payStrategy = payStrategy;
    }

    public String execute(Order order) {
        return this.payStrategy.pay(order);
    }
}