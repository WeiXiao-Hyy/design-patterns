package com.alipay.shop.repo.mapper;

import com.alipay.shop.model.ProductItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author hyy
 * @Description
 * @create 2023-12-17 22:11
 */
@Mapper
public interface ProductItemMapper extends BaseMapper<ProductItem> {

    List<ProductItem> findAll();

    @Select("select * from product_item where name = #{name} and pid = #{pid}")
    ProductItem findByNameAndPid(String name, int pid);

    /**
     * 在insert里面使用select时需要使用视图的方式进行查询
     * <p>
     *
     * @param name 名称
     * @param pid  父Id
     */
    @Insert("insert into product_item(id, name, pid) values ((select id from (select max(id)+1 as id from product_item) as pim ), #{name}, #{pid})")
    void addItem(@Param("name") String name, @Param("pid") int pid);

    /**
     * 目前只能删除深度为2的商品类目
     *
     * @param id 删除Id
     */
    @Delete("delete from product_item where id = #{id} or pid = #{id}")
    void delItem(@Param("id") int id);
}