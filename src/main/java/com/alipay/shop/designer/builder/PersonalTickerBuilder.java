package com.alipay.shop.designer.builder;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:47
 */
public class PersonalTickerBuilder extends TicketBuilder<PersonalTicket> {

    private PersonalTicket personalTicket = new PersonalTicket();

    @Override
    public void setCommonInfo(String title, String product, String content) {
        personalTicket.setTitle(title);
        personalTicket.setProduct(product);
        personalTicket.setContent(content);
    }

    @Override
    public PersonalTicket buildTicket() {
        return personalTicket;
    }
}