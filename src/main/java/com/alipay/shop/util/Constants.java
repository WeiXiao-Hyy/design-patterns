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
    public static final String APP_ID = "9021000133645149";

    /**
     * 应用公钥
     */
    public static final String APP_PRIVATE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApaQe1Z1lcO6Gv//1O41lF4oHrMNPSjS0uo0YvVqLWRXpD7zE/8z0EAsMqjLDU/rVyHHGbAxXys3ge45kBAm9Qc1SDaLTmtxcq61JaIIdr+Y4b7y5fRMdoPQUiFYSPcYHtgDbCSOsDZ8FeZ9Seibc3TafhzZJPM5aaqXmBSKkIRrZX9escXYVHJ6a7ZoaSnFea9eBoE6wnP+YH9GSmyaP3J5hDbBapFwqY6/6U2CUed6Rfac5Ahn9Q0g17rPqKP++HVa/JFimBnhe1G2OQ0njoEzgTdV3J3HqBaKbXEz4bKIidSjsddaptsI5wcq6H+RZY9ngge6BIx9w0pCKF2TYmQIDAQAB";

    /**
     * 支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkt1mRlOMfP4NckJB7G8Lq4sT1LqZgfNYAmoyTFMqKvhhW3sxAw6JtCNeINuam39CmdfJ8MCctHRZ5yynXeFvg2L16toy+RO164TvjpbyXhmWrdDoBDbc9Hs6lugl0JO98s14ecyuUUyW5pMqdolwv5CcSUYdJptvWWAv3s0k9Ldmh/tj6jMiaeVfX8gN0rHlntUrNartGUPTNW8igKDBhG0+OmN/MS7MFp01+on5RGQBf+G3QLJUnD4g3igdEQMDteoTeJHRj/c7XGRFhBOb6IgnpvRV/L24kuWdIo2IAMx+9aNx/m4JTO6aSPVUG3tnnavJvMkdbN/WCqxF1sBKAwIDAQAB";

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