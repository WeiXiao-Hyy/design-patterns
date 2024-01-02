package com.alipay.shop.designer.factory;

/**
 * @author hyy
 * @Description
 * @create 2024-01-02 00:02
 */
public abstract class AbstractPayContextFactory<T> {

    public abstract T getContext(Integer payType);
}