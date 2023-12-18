package com.alipay.shop.req;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-15 11:49
 */
@Data
@Component
public class AccessTokenDTO {
    private Integer id;
    private String clientId;
    private String redirectUri;
    private String code;
    private String state;
    private String clientSecret;
}