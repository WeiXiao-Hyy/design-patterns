package com.alipay.shop.designer.factory;

import com.alipay.shop.designer.listener.Order;

/**
 * @author hyy
 * @Description
 * @create 2024-01-02 00:05
 */
public abstract class AbstractPayContext {
    public abstract String execute(Order order);
}