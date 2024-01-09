package com.alipay.shop.designer.template;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 14:42
 */
@Component
public class PayOrderLog extends AbstractAuditLogProcessor {

    @Override
    protected OrderAuditLog buildDetails(OrderAuditLog auditLog) {
        Map<String, String> extraLogs = new HashMap<>();
        extraLogs.put("payType", "Alipay");
        auditLog.setDetails(extraLogs);
        return auditLog;
    }
}