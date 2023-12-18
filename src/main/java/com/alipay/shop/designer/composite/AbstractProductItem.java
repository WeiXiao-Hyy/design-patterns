package com.alipay.shop.designer.composite;

/**
 * @author hyy
 * @Description
 * @create 2023-12-16 22:26
 */
public abstract class AbstractProductItem {

    protected void addProductItem(AbstractProductItem item) {
        throw new UnsupportedOperationException();
    }

    protected void delProductItem(AbstractProductItem item) {
        throw new UnsupportedOperationException();
    }
}