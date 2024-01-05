package com.alipay.shop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author hyy
 * @Description
 * @create 2024-01-05 22:37
 */

@Data
@Builder
@TableName("`business_launch`")
public class BusinessLaunch {
    private Integer id;

    private String businessDetail;

    private String targetCity;

    private String targetSex;

    private String targetProduct;
}