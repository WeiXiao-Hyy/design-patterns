package com.alipay.shop.designer.builder;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:48
 */
public class CompanyTicketBuilder extends TicketBuilder<CompanyTicket>{

    private CompanyTicket companyTicket = new CompanyTicket();

    @Override
    public void setTaxId(String taxId) {
        companyTicket.setTaxId(taxId);
    }

    @Override
    public void setBankInfo(String bankInfo) {
        companyTicket.setBankInfo(bankInfo);
    }

    @Override
    public void setCommonInfo(String title, String product, String content) {
        companyTicket.setTitle(title);
        companyTicket.setProduct(product);
        companyTicket.setContent(content);
    }

    @Override
    public CompanyTicket buildTicket() {
        return companyTicket;
    }
}