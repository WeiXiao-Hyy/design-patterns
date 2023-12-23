package com.alipay.shop.designer.listener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hyy
 * @Description
 * @create 2023-12-20 22:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;

    private String productId;

    private OrderState orderState;

    private Float price;
}