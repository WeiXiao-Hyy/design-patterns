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
     * 支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkt1mRlOMfP4NckJB7G8Lq4sT1LqZgfNYAmoyTFMqKvhhW3sxAw6JtCNeINuam39CmdfJ8MCctHRZ5yynXeFvg2L16toy+RO164TvjpbyXhmWrdDoBDbc9Hs6lugl0JO98s14ecyuUUyW5pMqdolwv5CcSUYdJptvWWAv3s0k9Ldmh/tj6jMiaeVfX8gN0rHlntUrNartGUPTNW8igKDBhG0+OmN/MS7MFp01+on5RGQBf+G3QLJUnD4g3igdEQMDteoTeJHRj/c7XGRFhBOb6IgnpvRV/L24kuWdIo2IAMx+9aNx/m4JTO6aSPVUG3tnnavJvMkdbN/WCqxF1sBKAwIDAQAB";

    /**
     * 应用私钥
     */
    public static final String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQD5x4Z7XG52TLCgcIWoWzdmXhI0/zy/1/4JHTcwhviG1CcHsf9Xdf9jd3BQpkVZ1DYiqJMcLi2b00x18s0eTea9hukvQ1H4W+6xL74bC0kK6asxLxRisveIrSr9pJJNqKm9Bm6gjMS+o14i0TXq51aIG3crbeOqfTo8uI5pdNKfpb7OuQPH8MAjsINl4oLpyyWUy23lsIILO7b9cd3hfNDJ9j4ZsenDmV53mMxDP1d8BuxB5wwrKcEz4hD6Gn325jnZvKLEFaohSqenP39jBNJLpU6j4Y0H1b+Mm/SvCf6BODZM17Fp8IgbcqTz7oZuf1eWGgA7i6Zw68MtEQuyPYf7AgMBAAECggEATh+b7mWgnxupeuT2yQuOrlPp+e2DALX73LnokyMEBJfChWOk66Qadu/ajz8SHeCD9h8ns/61gTF7jsCca0gOTWUu9ftmXfnTFFRMb4T+GY2wX7fL9nrndTZEZgTdRbDlNhwW6dxdhQrv3I/2/zvQEs4UXd8HAEIevvMyh8VkQ04jynwV3NTkNUnllRU+su3xZhX9XIGONz2435h+w28PHi++R3V+m/K00JXXLokA8s0Wc+/iSPlFKebJYlAxcPG4Z6bSzXodIbkEj3h5QA0qfoFTHIm1kmUqTYk1sigzCwGbizSofdjB94yF1wBjiMEocH4SXX6VC493P9BYK0ruIQKBgQD+LnMPujDbzegu8lqP/hCUYVUFBzDqFRWBci4wvgfZ8OsGn5izyP/vOnO0uUa9j8TXV5pcN2VFELMs33XQLSESCJqrvAS78ECQQ/QoCb2nhHdZJPsu/8eyE/QAV0rk4cbfhbkyUs9xYoeOWrQbg9aaDQgm6xP2duYhLobROzkqyQKBgQD7kQNh67Hw8l5ctPAvXZIZQY7LjRvFths3ppLAmOfoBTG6H0GTFDxBwz5sL1LNrgmQ0YrHq6+59+v72C6V8eU14y0sp9bZT9xHMJNxnnypy6ch28IMQIVjljx1YyNFpOE712CXlmba0jcHd8E3rCFk97dRVtmenXjSSS3vSRf6owKBgQDsuH+qNwIHw0Uoa8cR8VCpj9gpXaAdcVNdAeSoumH/hBKuUXZagahxuONHfYG3SyzMTKbSmTi0ULA960MChPUWoXLC29Eg4siOUusRWrkJSSIJoJpaFBZ7738/zgxPC0I2q4hlqJ/Pfi620hyIv4jeYaKk9CvcTrFWV8lxFRHRmQKBgQD4HpQmfXCZBIQbjf6jyPdzX5y2mSu+Opnnrqxpt6Zrysu8Fl338epotmiYDaju4mve4T2vKF/+3U9sq1FvZRE8sB97PuOCnOjXckrKspWkZxAmLfjbWrv1eFWTNj+wzim0ev5zJM23n8TU4cG55lVdn5G4cR/c+NYVGkZrDz3OXwKBgQDc4zgUGC0wxiL1zxkXT9SSpvQAB8LJfBpNAHpRD7sxHjHLKpZ9wq5Qn4HT6ThG70re5SmQoM2mqvKrCArEM73F4Lcy5HAiU9Lp8zi3nCUboGhwTDU7YWpVyXpRSUcRQKfZsBuMDysYrOm2ZgbSy8xZ1Iynntb/64ZuPLR/8qOQjg==";

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