package com.alipay.shop.designer.builder;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:41
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyTicket implements Cloneable {
    private String finalInfo;

    private String title;

    private String taxId;

    private String bankInfo;

    private String product;

    private String content;

    @Override
    public CompanyTicket clone() {
        try {
            CompanyTicket clone = (CompanyTicket) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}