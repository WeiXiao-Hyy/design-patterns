package com.alipay.shop.repo.mapper;

import com.alipay.shop.model.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author hyy
 * @Description
 * @create 2023-12-14 21:56
 */

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {

    List<UserInfo> getAll();

    @Select("SELECT * FROM USER WHERE user_name=#{account} AND password=#{password}")
    UserInfo findByNameAndPassword(@Param("account") String account, @Param("password") String password);

    @Select("SELECT * FROM USER WHERE user_name=#{account}")
    UserInfo findByUserName(@Param("account") String account);
}