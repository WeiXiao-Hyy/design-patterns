package com.alipay.shop.designer.adapter;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2023/12/15 11:30
 * @see com.alipay.shop.designer.adapter
 */
public interface Login3rdTarget {
    String loginByGitee(String code, String state);

    String loginByWeChat(String... params);

    String loginByQQ(String... params);
}
