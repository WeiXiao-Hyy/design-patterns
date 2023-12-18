package com.alipay.shop.designer.composite.visitor;

import com.alipay.shop.designer.composite.AbstractProductItem;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2023/12/17 22:50
 * @see com.alipay.shop.designer.composite.visitor
 */
public interface ItemVisitor<T> {
    //定义公共的visitor方法提供子类实现
    T visitor(AbstractProductItem productItem);
}
