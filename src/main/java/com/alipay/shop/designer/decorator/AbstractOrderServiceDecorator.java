package com.alipay.shop.designer.decorator;

import com.alipay.shop.designer.listener.Order;
import lombok.Setter;

/**
 * @author hyy
 * @Description
 * @create 2024-01-08 16:58
 */
@Setter
public abstract class AbstractOrderServiceDecorator implements OrderServiceInterface {

    private OrderServiceInterface orderServiceInterface;

    @Override
    public Order create(String productId) {
        return this.orderServiceInterface.create(productId);
    }

    @Override
    public Order pay(String productId) {
       return this.orderServiceInterface.pay(productId);
    }

    @Override
    public Order send(String productId) {
        return this.orderServiceInterface.send(productId);
    }

    @Override
    public Order receive(String productId) {
        return this.orderServiceInterface.receive(productId);
    }

    @Override
    public String getUrl(String productId, Float price, Integer payType) {
        return this.orderServiceInterface.getUrl(productId, price, payType);
    }

    protected abstract void updateScoreAndSendRedPaper(String productId, int serviceLevel, float price);
}