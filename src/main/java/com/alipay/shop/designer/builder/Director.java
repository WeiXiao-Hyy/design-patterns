package com.alipay.shop.designer.builder;

import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:50
 */
@Component
public class Director extends AbstractDirector {

    @Override
    public Object buildTicket(String type, String product, String content, String title, String bankInfo, String taxId) {
        if ("person".equals(type)) {
            PersonalTickerBuilder personalTickerBuilder = new PersonalTickerBuilder();
            personalTickerBuilder.setCommonInfo(title, product, content);
            return personalTickerBuilder.buildTicket();
        } else if ("company".equals(type)) {
            CompanyTicketBuilder companyTicketBuilder = new CompanyTicketBuilder();
            companyTicketBuilder.setCommonInfo(title, product, content);
            companyTicketBuilder.setBankInfo(bankInfo);
            companyTicketBuilder.setTaxId(taxId);
            return companyTicketBuilder.buildTicket();
        }
        throw new UnsupportedOperationException();
    }
}