package com.alipay.shop.designer.dutyChain;

import com.alipay.api.internal.util.StringUtils;
import com.alipay.shop.model.BusinessLaunch;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hyy
 * @Description
 * @create 2024-01-05 23:11
 */
public class SexHandler extends AbstractBusinessHandler {

    @Override
    public List<BusinessLaunch> processHandler(List<BusinessLaunch> launchList, String targetCity, String targetSex, String targetProduct) {
        if (launchList.isEmpty()) {
            return launchList;
        }

        //按照target进行筛选,只保留满足条件的投放信息
        launchList = launchList.stream().filter(launch -> {
            String sex = launch.getTargetSex();
            if (StringUtils.isEmpty(sex)) {
                return true;
            }
            return sex.equals(targetSex);
        }).collect(Collectors.toList());

        //如果还有下一个责任链,则继续进行传递
        if (hasNextHandler()) {
            return nextHandler.processHandler(launchList, targetCity, targetSex, targetProduct);
        }

        return launchList;
    }
}