package com.alipay.shop.designer.bridge.service;

import com.alipay.shop.designer.bridge.factory.RegisterLoginComponentFactory;
import com.alipay.shop.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2023-12-15 18:08
 */
@Service
public class UserBridgeService {

    public String login(String account, String password) {
        //每次执行login的时候都需要创建两个对象,可能会引起频繁垃圾回收
        AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("Default");
        return registerLoginComponent.login(account, password);
    }

    public String register(UserInfo userInfo) {
        AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("Default");
        return registerLoginComponent.register(userInfo);
    }

    public String login3rd(HttpServletRequest request, String type) {
        AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent(type);
        return registerLoginComponent.login3rd(request);
    }
}