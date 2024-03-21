package com.alipay.shop.util;

/**
 * @author hyy
 * @Description
 * @create 2023-12-27 23:47
 */
public class Constants {

    /**
     * APPID
     */
    public static final String APP_ID = "#";

    /**
     * 支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY = "#";

    /**
     * 应用私钥
     */
    public static final String APP_PRIVATE_KEY = "#";

    /**
     * 沙箱接口路径
     */
    public static final String ALIPAY_GATEWAY = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "RSA2";

    /**
     * 接口回调地址
     */
    public static final String CALLBACK_URL = "http://localhost:8081/order/alipaycallback";

}