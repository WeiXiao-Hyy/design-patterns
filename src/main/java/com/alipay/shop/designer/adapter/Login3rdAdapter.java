package com.alipay.shop.designer.adapter;

import com.alibaba.fastjson.JSON;
import com.alipay.shop.model.GiteeUser;
import com.alipay.shop.model.UserInfo;
import com.alipay.shop.req.AccessTokenDTO;
import com.alipay.shop.designer.adapter.service.UserAdapterService;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-15 11:29
 */
@Component
public class Login3rdAdapter extends UserAdapterService implements Login3rdTarget {

    @Autowired
    private AccessTokenDTO accessTokenDTO;

    @Value("${gitee.client_id}")//获取配置文件中的数据
    private String client_id;

    @Value("${gitee.redirect_uri}")
    private String redirect_uri;

    @Value("${gitee.client_secret}")
    private String client_secret;

    @Value("${gitee.user_prefix}")
    private String user_prefix;

    @Override
    public String loginByGitee(String code, String state) {

        // 准备请求参数
        prepareAccessTokenReq(code, state);

        //调用方法得到accessToken
        String accessToken = getAccessToken(accessTokenDTO);

        //调用方法得到用户信息
        GiteeUser user = getUser(accessToken);

        // 自动注册和登陆
        return autoRegister3rdAndLogin(user);
    }

    @Override
    public String loginByWeChat(String... params) {
        return null;
    }

    @Override
    public String loginByQQ(String... params) {
        return null;
    }

    private String autoRegister3rdAndLogin(GiteeUser giteeUser) {
        //判断是否为null
        if (Objects.isNull(giteeUser)) {
            return "user is not exists";
        }

        //获取用户名和密码
        String userName = user_prefix + giteeUser.getName();
        String password = giteeUser.getName();

        //如果存在则直接登陆
        if (super.checkUserExists(userName)) {
            return super.login(userName, password);
        }

        //不存在则先注册再登陆
        UserInfo user = UserInfo.builder()
                .userName(userName)
                .password(password)
                .email(giteeUser.getEmail())
                .build();

        super.register(user);

        return super.login(userName, password);
    }

    /**
     * 根据code,state进行获取token
     *
     * @param code  回调code
     * @param state 回调state
     */
    private void prepareAccessTokenReq(String code, String state) {
        accessTokenDTO.setClientId(client_id);
        accessTokenDTO.setRedirectUri(redirect_uri);
        accessTokenDTO.setClientSecret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
    }

    /**
     * 获取gitee token
     *
     * @param accessTokenDto 请求体{@link AccessTokenDTO}
     * @return token
     */
    private String getAccessToken(AccessTokenDTO accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        //将对象转化为JSON格式
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));

        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code=" + accessTokenDto.getCode() + "&client_id=" + accessTokenDto.getClientId() + "&redirect_uri=" + accessTokenDto.getRedirectUri() + "&client_secret=" + accessTokenDto.getClientSecret())
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return string.split(":")[1].split("\"")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 使用token获取gitee用户
     *
     * @param accessToken token
     * @return {@link GiteeUser}
     */
    private GiteeUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            return JSON.parseObject(str, GiteeUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}