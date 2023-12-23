package com.alipay.shop.designer.bridge;

import com.alipay.shop.model.UserInfo;
import javax.servlet.http.HttpServletRequest;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2023/12/15 13:28
 * @see com.alipay.shop.designer.bridge
 */
public interface IRegisterLoginFunction {
    String login(String account, String password);

    String register(UserInfo userInfo);

    boolean checkUserExists(String userName);

    String login3rd(HttpServletRequest request);
}
