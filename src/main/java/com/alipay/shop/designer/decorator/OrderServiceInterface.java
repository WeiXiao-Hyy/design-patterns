package com.alipay.shop.designer.decorator;

import com.alipay.shop.designer.listener.Order;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2024/1/8 16:45
 * @see com.alipay.shop.designer.decorator
 */
public interface OrderServiceInterface {
    Order create(String productId);

    Order pay(String productId);

    Order send(String productId);

    Order receive(String productId);

    String getUrl(String productId, Float price, Integer payType);
}
