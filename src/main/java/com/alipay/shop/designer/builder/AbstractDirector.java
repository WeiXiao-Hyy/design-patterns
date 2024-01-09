package com.alipay.shop.designer.builder;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:49
 */
public abstract class AbstractDirector {
    public abstract Object buildTicket(String type, String product, String content, String title, String bankInfo, String taxId);
}