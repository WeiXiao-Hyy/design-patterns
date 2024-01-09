package com.alipay.shop.designer.builder;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:44
 */
public abstract class TicketBuilder<T> {
    public abstract void setCommonInfo(String title, String product, String content);


    public void setTaxId(String taxId) {
        throw new UnsupportedOperationException();
    }

    public void setBankInfo(String bankInfo) {
        throw new UnsupportedOperationException();
    }

    public abstract T buildTicket();
}