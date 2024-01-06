package com.alipay.shop.designer.dutyChain.service;

import com.alipay.shop.designer.dutyChain.AbstractBusinessHandler;
import com.alipay.shop.designer.dutyChain.CityHandler;
import com.alipay.shop.designer.dutyChain.enums.HandlerEnum;
import com.alipay.shop.model.BusinessLaunch;
import com.alipay.shop.repo.mapper.BusinessLaunchMapper;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2024-01-06 11:59
 */
@Slf4j
@Service
public class UserDutyChainService {

    @Resource
    private BusinessLaunchMapper businessLaunchRepo;

    //获取Apollo配置
    @Value("${duty.chain}")
    private String handlerType;

    private String currentHandlerType;

    private AbstractBusinessHandler currentHandler;

    public List<BusinessLaunch> filterBusinessLaunch(String city, String sex, String product) {
        List<BusinessLaunch> launchList = businessLaunchRepo.findAll();
        AbstractBusinessHandler headHandler = buildChain();
        if (Objects.isNull(headHandler)) {
            return Collections.emptyList();
        }
        return headHandler.processHandler(launchList, city, sex, product);
    }

    //组装责任链条并返回责任链条首节点
    private AbstractBusinessHandler buildChain() {

        //如果没有配置,直接返回null
        if (Objects.isNull(this.handlerType)) {
            return null;
        }

        if (Objects.isNull(this.currentHandlerType)) {
            this.currentHandlerType = this.handlerType;
        }

        if (this.currentHandlerType.equals(this.handlerType) && Objects.nonNull(this.currentHandler)) {
            return this.currentHandler;
        } else {
            log.info("config modified or first init, start to assemble duty chain.");
            synchronized (this) {
                try {
                    //伪头节点
                    AbstractBusinessHandler dummyHeadHandler = new CityHandler();
                    AbstractBusinessHandler preHandler = dummyHeadHandler;

                    String[] handlerTypeList = this.handlerType.split(",");
                    for (String handlerType : handlerTypeList) {
                        AbstractBusinessHandler handler = (AbstractBusinessHandler)
                                Class.forName(HandlerEnum.valueOf(handlerType).getValue()).newInstance();
                        preHandler.nextHandler = handler;
                        preHandler = handler;
                    }

                    this.currentHandler = dummyHeadHandler.nextHandler;
                    this.currentHandlerType = this.handlerType;

                    return this.currentHandler;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    throw new UnsupportedOperationException(e);
                }
            }
        }
    }
}