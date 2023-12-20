package com.alipay.shop.designer.state.observer;

import com.alipay.shop.designer.state.observer.constrants.DeprecatedConstants;
import jakarta.annotation.PostConstruct;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 22:45
 */
public class DeprecatedCreateObserver extends DeprecatedAbstractObserver {

    //将自己注册到集合中
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }

    @Override
    public void orderStateHandler(String orderId, String orderState) {
        //订单初始化,状态必须是ORDER_WAIT_PAY状态
        if (!orderState.equals("ORDER_WAIT_PAY")) {
            return;
        }
        System.out.println("监听到,订单创建成功. 通过命令模式作为后续操作");
    }
}