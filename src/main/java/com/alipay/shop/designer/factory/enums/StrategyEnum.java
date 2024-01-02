package com.alipay.shop.designer.factory.enums;

import lombok.Getter;

/**
 * @author hyy
 * @Description
 * @create 2024-01-02 00:07
 */
@Getter
public enum StrategyEnum {
    alipay("com.alipay.shop.designer.strategy.impl.AlipayStrategy"),
    wechat("com.alipay.shop.designer.strategy.impl.WechatStrategy"),
    ;

    String value = "";

    StrategyEnum(String value) {
        this.value = value;
    }

}