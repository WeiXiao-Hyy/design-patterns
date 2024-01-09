package com.alipay.shop.designer.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalTicket {

    //发票固定不变的信息
    private String finalInfo;

    private String title;

    private String product;

    private String content;
}