package com.alipay.shop.model;

/**
 * @author hyy
 * @Description
 * @create 2024-01-08 17:25
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("`products`")
public class Products {
    private Integer id;

    private String productId;

    private Integer sendRedBag;
}