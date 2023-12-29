package com.alipay.shop.designer.strategy.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.designer.strategy.PayStrategyInterface;
import com.alipay.shop.util.Constants;
import org.jetbrains.annotations.NotNull;

/**
 * @author hyy
 * @Description
 * @create 2023-12-25 16:21
 */
public class AlipayStrategy implements PayStrategyInterface {

    @Override
    public String pay(Order order) {

        //创建AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(Constants.ALIPAY_GATEWAY,
                Constants.APP_ID,
                Constants.APP_PRIVATE_KEY,
                "json",
                "UTF-8",
                Constants.ALIPAY_PUBLIC_KEY,
                Constants.SIGN_TYPE);

        //设置请求参数
        AlipayTradePagePayRequest payRequest = getAlipayTradePagePayRequest(order);

        //请求
        try {
            return alipayClient.pageExecute(payRequest, "GET").getBody();
        } catch (AlipayApiException e) {
            throw new UnsupportedOperationException("Alipay failed! " + e);
        }
    }

    @NotNull
    private static AlipayTradePagePayRequest getAlipayTradePagePayRequest(Order order) {
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(Constants.CALLBACK_URL);
        payRequest.setBizContent("{\n" +
                "    \"out_trade_no\":\"" + order.getOrderId() + "\",\n" +
                "    \"total_amount\": \"" + order.getPrice() + "\",\n" +
                "    \"subject\": \"伟山育琪\",\n" +
                "    \"body\": \"商品描述\",\n" +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"\n" +
                "}");
        return payRequest;
    }
}