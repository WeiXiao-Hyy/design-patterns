package com.alipay.shop.designer.state.deprecated;

import com.alipay.shop.designer.state.deprecated.observer.DeprecatedAbstractObserver;
import java.util.List;
import java.util.Vector;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 19:18
 */
public abstract class DeprecatedAbstractOrderState {

    protected final String ORDER_WAIT_PAY = "ORDER_WAIT_PAY";

    protected final String ORDER_WAIT_SEND = "ORDER_WAIT_SEND";

    protected final String ORDER_WAIT_RECEIVE = "ORDER_WAIT_RECEIVE";

    protected final String ORDER_FINISH = "ORDER_FINISH";

    //在SpringBoot框架中无法优雅地将几个观察者注入到List中去
    protected final List<DeprecatedAbstractObserver> observerList = new Vector<>();

    public void addObserver(DeprecatedAbstractObserver observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(DeprecatedAbstractObserver observer) {
        this.observerList.remove(observer);
    }

    public void notifyObserver(String orderId, String orderState) {
        for (DeprecatedAbstractObserver observer : observerList) {
            observer.orderStateHandler(orderId, orderState);
        }
    }

    protected DeprecatedOrder createOrder(String orderId, String productId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder payOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder sendOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder receiveOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }
}