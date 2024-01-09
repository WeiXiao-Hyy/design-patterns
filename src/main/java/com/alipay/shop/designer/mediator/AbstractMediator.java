package com.alipay.shop.designer.mediator;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 13:45
 */
public abstract class AbstractMediator {
    /**
     * 中介者消息传递
     * @param orderId 订单Id
     * @param targetCustomer 目标用户
     * @param customer 调用者的身份
     * @param payResult 支付完成结果
     */
    public abstract void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult);
}