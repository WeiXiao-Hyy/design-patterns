package com.alipay.shop.designer.state.deprecated.observer;

import com.alipay.shop.designer.state.deprecated.observer.constrants.DeprecatedConstants;
import javax.annotation.PostConstruct;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 23:02
 */
public class DeprecatePayObserver extends DeprecatedAbstractObserver {

    //将自己注册到集合中
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }

    @Override
    public void orderStateHandler(String orderId, String orderState) {
        //订单初始化,状态必须是ORDER_WAIT_SEND状态
        if (!orderState.equals("ORDER_WAIT_SEND")) {
            return;
        }
        System.out.println("监听到,订单创建成功. 通过命令模式作为后续操作");
    }
}