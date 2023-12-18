package com.alipay.shop.designer.bridge.factory;

import com.alipay.shop.designer.bridge.IRegisterLoginFunction;
import com.alipay.shop.designer.bridge.service.AbstractRegisterLoginComponent;
import com.alipay.shop.designer.bridge.service.RegisterLoginComponent;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hyy
 * @Description
 * @create 2023-12-15 21:11
 */
public class RegisterLoginComponentFactory {

    public static final Map<String, AbstractRegisterLoginComponent> componentMap
            = new ConcurrentHashMap<>();

    public static Map<String, IRegisterLoginFunction> funcMap
            = new ConcurrentHashMap<>();

    /**
     * 根据不同的登陆类型，获取AbstractRegisterLoginComponent
     *
     * @param type 类型
     * @return {@link AbstractRegisterLoginComponent}
     */
    public static AbstractRegisterLoginComponent getComponent(String type) {
        AbstractRegisterLoginComponent component = componentMap.get(type);

        // 双重检查锁设计
        // 防止每次都需要锁map
        if (Objects.isNull(component)) {
            synchronized (componentMap) {
                component = componentMap.get(type);
                if (Objects.isNull(component)) {
                    component = new RegisterLoginComponent(funcMap.get(type));
                    //由于concurrentHashMap本身是并发安全的,所有不需要volatile
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }
}