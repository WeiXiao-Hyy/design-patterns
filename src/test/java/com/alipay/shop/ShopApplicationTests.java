package com.alipay.shop;

import com.alipay.shop.model.UserInfo;
import com.alipay.shop.repo.mapper.UserMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ShopApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectUsers() {
        List<UserInfo> userInfoList = userMapper.getAll();
        Assert.isTrue(5 == userInfoList.size(), "");
        userInfoList.forEach(System.out::println);
    }

    @Test
    public void testSelectUserByNameAndPassword() {
        UserInfo user = userMapper.findByNameAndPassword("Jone", "991011");
        Assert.notNull(user, "");
    }

    @Test
    public void testSelectUserByName() {
        UserInfo user = userMapper.findByUserName("Jone");
        Assert.notNull(user, "");
    }

}
