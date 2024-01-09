package com.alipay.shop.designer.template;

import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 14:39
 */
@Component
public class ReceiveOrderLog extends AbstractAuditLogProcessor{

    @Override
    protected OrderAuditLog buildDetails(OrderAuditLog auditLog) {
        return auditLog;
    }
}