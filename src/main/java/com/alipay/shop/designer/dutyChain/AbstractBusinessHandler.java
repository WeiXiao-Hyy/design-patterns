package com.alipay.shop.designer.dutyChain;

import com.alipay.shop.model.BusinessLaunch;
import java.util.List;

/**
 * @author hyy
 * @Description
 * @create 2024-01-05 23:11
 */
public abstract class AbstractBusinessHandler {
    public AbstractBusinessHandler nextHandler;

    public boolean hasNextHandler() {
        return this.nextHandler != null;
    }

    /**
     * @param launchList    责任链列表
     * @param targetCity    业务投放-目标城市
     * @param targetSex     业务投放-目标性别
     * @param targetProduct 业务投放-目标商品
     * @return 满足条件的责任链列表
     */
    public abstract List<BusinessLaunch> processHandler(List<BusinessLaunch> launchList,
                                                        String targetCity,
                                                        String targetSex,
                                                        String targetProduct);
}