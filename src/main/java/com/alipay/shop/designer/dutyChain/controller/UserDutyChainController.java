package com.alipay.shop.designer.dutyChain.controller;

import com.alipay.shop.designer.dutyChain.service.UserDutyChainService;
import com.alipay.shop.model.BusinessLaunch;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2024-01-06 12:35
 */
@RestController
@RequestMapping("/dutyChain")
public class UserDutyChainController {

    @Resource
    private UserDutyChainService userDutyChainService;

    @PostMapping("/business/launch")
    public List<BusinessLaunch> filterBusinessLaunch(@RequestParam("city") String city, @RequestParam("sex") String sex,
                                                     @RequestParam("product") String product) {
        return userDutyChainService.filterBusinessLaunch(city, sex, product);
    }
}