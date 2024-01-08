package com.alipay.shop.repo.mapper;

import com.alipay.shop.model.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hyy
 * @Description
 * @create 2024-01-08 17:28
 */
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {

    @Select("select * from products where product_id = #{productId}")
    Products findProductsByProductId(String productId);
}