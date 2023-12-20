package com.alipay.shop.designer.state;

/**
 * @author hyy
 * @Description
 * @create 2023-12-20 22:18
 */
public enum OrderState {
    ORDER_WAIT_PAY, //等待支付
    ORDER_WAIT_SEND, //等待发货
    ORDER_WAIT_RECEIVE, //等待收货
    ORDER_FINISH // 订单完成
}