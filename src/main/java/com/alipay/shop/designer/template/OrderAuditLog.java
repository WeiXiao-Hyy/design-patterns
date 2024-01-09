package com.alipay.shop.designer.template;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 14:33
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAuditLog {
    private String account;

    private String action;

    private Date date;

    private String orderId;

    private Object details;
}