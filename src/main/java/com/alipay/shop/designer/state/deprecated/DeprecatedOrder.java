package com.alipay.shop.designer.state.deprecated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2023-12-19 19:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeprecatedOrder {
    private String orderId;

    private String productId;

    //订单状态
    private String state;
}