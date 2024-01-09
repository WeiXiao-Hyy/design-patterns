package com.alipay.shop.designer.mediator;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 13:46
 */
public abstract class AbstractCustomer {
    public AbstractMediator mediator;

    public String orderId;

    public String customerName;

    public AbstractCustomer(AbstractMediator mediator, String orderId, String customerName) {
        this.mediator = mediator;
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public abstract void messageTransfer(String orderId, String targetCustomer, String payResult);
}