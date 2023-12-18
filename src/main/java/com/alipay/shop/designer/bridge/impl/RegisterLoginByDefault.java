package com.alipay.shop.designer.bridge.impl;

import com.alipay.shop.designer.bridge.IRegisterLoginFunction;
import com.alipay.shop.designer.bridge.factory.RegisterLoginComponentFactory;
import com.alipay.shop.model.UserInfo;
import com.alipay.shop.repo.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-15 13:30
 */
@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements IRegisterLoginFunction {

    @Autowired
    private UserMapper userRepo;

    @PostConstruct
    public void initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("Default", this);
    }

    @Override
    public String login(String account, String password) {
        return super.commonLogin(account, password, userRepo);
    }

    @Override
    public String register(UserInfo userInfo) {
        return super.commonRegister(userInfo, userRepo);
    }

    @Override
    public boolean checkUserExists(String userName) {
        return super.commonCheckUserExists(userName, userRepo);
    }
}