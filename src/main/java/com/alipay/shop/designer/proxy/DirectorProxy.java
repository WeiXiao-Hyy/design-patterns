package com.alipay.shop.designer.proxy;

import com.alipay.shop.designer.builder.AbstractDirector;
import com.alipay.shop.designer.builder.Director;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 12:19
 */
@Slf4j
@Component
public class DirectorProxy extends AbstractDirector {

    @Resource
    private Director director;

    @Override
    public Object buildTicket(String type, String productId, String content, String title, String bankInfo, String taxId) {

        String product = this.getProduct(productId);

        if (Objects.isNull(bankInfo) || !validateBankInfo(bankInfo)) {
            return null;
        }
        return director.buildTicket(type, product, content, title, bankInfo, taxId);
    }

    private String getProduct(String productId) {
        return "通过productId获取Product信息";
    }


    private boolean validateBankInfo(String bankInfo) {
        log.info("bank info validate success");
        return true;
    }

}