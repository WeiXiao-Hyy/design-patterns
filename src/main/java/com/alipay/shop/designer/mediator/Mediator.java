package com.alipay.shop.designer.mediator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 13:50
 */
@Slf4j
@Component
public class Mediator extends AbstractMediator {

    public Map<String, Map<String, AbstractCustomer>> customerInstances = new ConcurrentHashMap<>();

    @Override
    public void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult) {
        if (customer instanceof Buyer) {
            AbstractCustomer buyer = customerInstances.get(orderId).get("buyer");
            log.info("朋友代替付款: " + buyer.customerName + " 转发OrderId= " + orderId + "到用户" + targetCustomer + "进行支付.");
        } else if (customer instanceof Payer) {
            AbstractCustomer payer = customerInstances.get(orderId).get("payer");
            log.info("代付完成:" + payer.customerName + " 完成OrderId= " + orderId + "的支付. 通知" + targetCustomer + "支付结果");
        }
        customerInstances.remove(orderId);
    }
}