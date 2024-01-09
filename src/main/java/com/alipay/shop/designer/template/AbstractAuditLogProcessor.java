package com.alipay.shop.designer.template;

import java.util.Date;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 14:34
 */
public abstract class AbstractAuditLogProcessor {
    private OrderAuditLog basicAuditLog(String account, String action, String orderId) {
        OrderAuditLog auditLog = new OrderAuditLog();
        auditLog.setAccount(account);
        auditLog.setAction(action);
        auditLog.setOrderId(orderId);
        auditLog.setDate(new Date());
        return auditLog;
    }

    protected abstract OrderAuditLog buildDetails(OrderAuditLog auditLog);

    public final OrderAuditLog createAuditLog(String account, String action, String orderId) {
        //设置审计日志的基本信息
        OrderAuditLog orderAuditLog = basicAuditLog(account, action, orderId);
        //设置审计日志的额外信息
        return buildDetails(orderAuditLog);
    }
}