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
public class CompanyTicket {
    private String finalInfo;

    private String title;

    private String taxId;

    private String bankInfo;

    private String product;

    private String content;
}