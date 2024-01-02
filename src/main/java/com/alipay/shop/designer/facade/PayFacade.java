package com.alipay.shop.designer.facade;

import com.alipay.shop.designer.factory.impl.PayContextFactory;
import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.designer.strategy.PayContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-29 23:54
 */
@Component
public class PayFacade {

    @Autowired
    private PayContextFactory contextFactory;

    public String pay(Order order, Integer payType) {
        PayContext context = contextFactory.getContext(payType);
        return context.execute(order);
    }
}