package com.alipay.shop.designer.decorator;

import com.alipay.shop.model.Products;
import com.alipay.shop.repo.mapper.ProductsMapper;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2024-01-08 17:17
 */
@Service
@Slf4j
public class OrderServiceDecorator extends AbstractOrderServiceDecorator {

    @Value("${delay.service.time}")
    private String delayServiceTime;

    @Resource
    private ProductsMapper productsRepo;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    protected void updateScoreAndSendRedPaper(String productId, int serviceLevel, float price) {
        switch (serviceLevel) {
            case 0:
                int score = Math.round(price) / 100;
                log.info("normal process update user score! score={}", score);
                //获取商品实体
                Products p = productsRepo.findProductsByProductId(productId);
                if (!ifSendRedBag(p)) {
                    break;
                }
            case 1:
                MessageProperties properties = new MessageProperties();
                //设置消息过期时间
                properties.setExpiration(delayServiceTime);
                Message msg = new Message(productId.getBytes(), properties);
                //向正常队列中发送消息
                rabbitTemplate.send("normalExchange", "myRKey", msg);
                log.info("delay process, time= " + delayServiceTime);
                break;
            case 2:
                log.info("stop service");
            default:
                throw new UnsupportedOperationException("not supported message type");
        }
    }

    private boolean ifSendRedBag(Products p) {
        return Objects.nonNull(p) && p.getSendRedBag() == 1;
    }


    public void payDecorator(String productId, int serviceLevel, float price) {
        super.pay(productId);
        try {
            updateScoreAndSendRedPaper(productId, serviceLevel, price);
        } catch (Exception e) {
            // 重试机制,这里失败不能影响主流程(弱依赖)
        }
    }
}