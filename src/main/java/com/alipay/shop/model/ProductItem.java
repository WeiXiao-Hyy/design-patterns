package com.alipay.shop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author hyy
 * @Description
 * @create 2023-12-16 22:21
 */

@Data
@Builder
@TableName("`product_item`")
public class ProductItem {
    /**
     * id
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父id
     */
    private int pid;
}