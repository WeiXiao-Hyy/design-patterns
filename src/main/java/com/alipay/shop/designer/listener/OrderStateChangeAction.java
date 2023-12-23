package com.alipay.shop.designer.listener;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2023/12/20 22:19
 * @see com.alipay.shop.designer.state
 */
public enum OrderStateChangeAction {
    PAY_ORDER, // 支付动作
    SEND_ORDER, // 发货动作
    RECEIVE_ORDER // 收货动作
}
