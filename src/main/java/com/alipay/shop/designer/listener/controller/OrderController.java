package com.alipay.shop.designer.listener.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.shop.designer.decorator.OrderServiceDecorator;
import com.alipay.shop.designer.listener.service.OrderService;
import com.alipay.shop.designer.listener.Order;
import com.alipay.shop.util.Constants;
import com.alipay.shop.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2023-12-22 22:57
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${service.level}")
    private Integer serviceLevel;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderServiceDecorator orderServiceDecorator;

    @PostMapping("/create")
    public Order createOrder(@RequestParam String productId) {
        return orderService.create(productId);
    }

    @PostMapping("/pay")
    public String payOrder(@RequestParam String productId,
                           @RequestParam Float price,
                           @RequestParam Integer payType) {
        return orderService.getUrl(productId, price, payType);
    }

    @PostMapping("/send")
    public Order send(@RequestParam String productId) {
        return orderService.send(productId);
    }

    @PostMapping("/receive")
    public Order receive(@RequestParam String productId) {
        return orderService.receive(productId);
    }

    @RequestMapping("/alipaycallback")
    public String alipayCallback(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {

        //1.获取回调信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            valueStr = StringUtils.getUTF8ParamsFromISO(valueStr);
            params.put(name, valueStr);
        }

        //2.验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, Constants.ALIPAY_PUBLIC_KEY, "UTF-8", Constants.SIGN_TYPE);

        //3.确定是支付宝平台发起的回调
        if (signVerified) {
            String out_trade_no = StringUtils.getUTF8ParamsFromISO(request.getParameter("out_trade_no"));
            String trade_no = StringUtils.getUTF8ParamsFromISO(request.getParameter("trade_no"));
            float total_amount = Float.parseFloat(StringUtils.getUTF8ParamsFromISO(request.getParameter("total_amount")));
            //进行相关业务操作，修改订单为待发货状态
            //orderService.pay(out_trade_no);
            //使用装饰器模式
            orderServiceDecorator.payDecorator(out_trade_no, serviceLevel, total_amount);
            return "支付成功页面跳转, out_trade_no= " + out_trade_no + "trade_no= " + trade_no + "total_amount= " + total_amount;
        } else {
            throw new UnsupportedEncodingException("alipay callback verify failed");
        }
    }
}