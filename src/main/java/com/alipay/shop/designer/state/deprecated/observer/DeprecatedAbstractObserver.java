package com.alipay.shop.designer.state.deprecated.observer;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 22:44
 */
public abstract class DeprecatedAbstractObserver {

    public abstract void orderStateHandler(String orderId, String orderState);
}