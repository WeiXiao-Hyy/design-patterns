package com.alipay.shop.designer.factory.impl;

import com.alipay.shop.designer.factory.AbstractPayContextFactory;
import com.alipay.shop.designer.factory.enums.StrategyEnum;
import com.alipay.shop.designer.strategy.PayContext;
import com.alipay.shop.designer.strategy.PayStrategyInterface;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-01-02 00:04
 */
@Component
public class PayContextFactory extends AbstractPayContextFactory<PayContext> {

    //享元模式
    private static final Map<String, PayContext> payContexts = new ConcurrentHashMap<>();

    @Override
    public PayContext getContext(Integer payType) {
        StrategyEnum strategyEnum =
                payType == 1 ? StrategyEnum.alipay :
                        payType == 2 ? StrategyEnum.wechat :
                                null;
        if (Objects.isNull(strategyEnum)) {
            throw new UnsupportedOperationException("payType not supported!");
        }

        //尝试从map中获取Context
        PayContext context = payContexts.get(strategyEnum.name());

        //第一次调用
        if (Objects.isNull(context)) {
            synchronized (payContexts) {
                context = payContexts.get(strategyEnum.name());
                if (Objects.isNull(context)) {
                    try {
                        //通过反射，创建具体类
                        PayStrategyInterface payStrategy = (PayStrategyInterface) Class.forName(strategyEnum.getValue()).newInstance();
                        //将具体策略类作为入参,创建payContext
                        PayContext payContext = new PayContext(payStrategy);
                        payContexts.put(strategyEnum.name(), payContext);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        throw new UnsupportedOperationException("get strategy failed!");
                    }
                }
            }
        }
        return payContexts.get(strategyEnum.name());
    }
}