package com.alipay.shop.designer.facade;

import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.designer.strategy.PayContext;
import com.alipay.shop.designer.strategy.impl.AlipayStrategy;
import com.alipay.shop.designer.strategy.impl.WechatStrategy;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-29 23:54
 */
@Component
public class PayFacade {
    public String pay(Order order, Integer payType) {
        switch (payType) {
            case 1:
                AlipayStrategy alipayStrategy = new AlipayStrategy();
                PayContext alipayContext = new PayContext(alipayStrategy);
                return alipayContext.execute(order);
            case 2:
                WechatStrategy wechatStrategy = new WechatStrategy();
                PayContext wechatContext = new PayContext(wechatStrategy);
                return wechatContext.execute(order);
            default:
                throw new UnsupportedOperationException("Pay Type not supported!");
        }
    }
}