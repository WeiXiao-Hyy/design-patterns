package com.alipay.shop.designer.adapter.service;

import com.alipay.shop.model.UserInfo;
import com.alipay.shop.repo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2023-12-14 23:14
 */
@Service
public class UserAdapterService {

    @Autowired
    private UserMapper userRepo;

    public String login(String account, String password) {
        UserInfo userInfo = userRepo.findByNameAndPassword(account, password);
        if (null == userInfo) {
            return "account / password ERROR!";
        }
        return "Login Success";
    }

    public String register(UserInfo userInfo) {
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("User already registered.");
        }
        userRepo.insert(userInfo);
        return "Register Success";
    }

    public boolean checkUserExists(String username) {
        UserInfo user = userRepo.findByUserName(username);
        return null != user;
    }
}