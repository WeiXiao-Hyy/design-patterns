package com.alipay.shop.designer.mediator;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 13:49
 */
public class Buyer extends AbstractCustomer {

    public Buyer(AbstractMediator mediator, String orderId, String customerName) {
        super(mediator, orderId, customerName);
    }

    @Override
    public void messageTransfer(String orderId, String targetCustomer, String payResult) {
        super.mediator.messageTransfer(orderId, targetCustomer, this, payResult);
    }
}