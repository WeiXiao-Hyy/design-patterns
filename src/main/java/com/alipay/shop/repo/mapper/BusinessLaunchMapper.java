package com.alipay.shop.repo.mapper;

import com.alipay.shop.model.BusinessLaunch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2024/1/5 22:39
 * @see com.alipay.shop.repo.mapper
 */
@Mapper
public interface BusinessLaunchMapper extends BaseMapper<BusinessLaunch> {

    @Select("select * from business_launch")
    List<BusinessLaunch> findAll();
}
