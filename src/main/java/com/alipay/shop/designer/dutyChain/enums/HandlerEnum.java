package com.alipay.shop.designer.dutyChain.enums;

import lombok.Getter;

/**
 * File Description.
 *
 * @author hyy
 * @date crated at 2025/1/6 12:01
 * @see com.alipay.shop.designer.dutyChain.enums
 */
@Getter
public enum HandlerEnum {
    city("com.alipay.shop.designer.dutyChain.CityHandler"),
    sex("com.alipay.shop.designer.dutyChain.SexHandler"),
    product("com.alipay.shop.designer.dutyChain.ProductHandler");

    String value="";
    HandlerEnum(String value) {
        this.value = value;
    }

}