package com.alipay.shop.util;

import java.nio.charset.StandardCharsets;

/**
 * @author hyy
 * @Description
 * @create 2023-12-29 23:37
 */
public class StringUtils {

    public static String getUTF8ParamsFromISO(String s) {
        return new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}