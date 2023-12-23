package com.alipay.shop.config;

import com.alipay.shop.designer.listener.OrderState;
import com.alipay.shop.designer.listener.OrderStateChangeAction;
import java.util.EnumSet;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

/**
 * @author hyy
 * @Description
 * @create 2023-12-20 22:22
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderStateChangeAction> {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    //持久化状态机
    @Bean(name = "stateMachineRedisPersister")
    public RedisStateMachinePersister<OrderState, OrderStateChangeAction> getRedisPersister() {
        RedisStateMachineContextRepository<OrderState, OrderStateChangeAction> repo = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        RepositoryStateMachinePersist<OrderState, OrderStateChangeAction> p = new RepositoryStateMachinePersist<>(repo);
        return new RedisStateMachinePersister<>(p);
    }

    //初始化方法
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderStateChangeAction> states) throws Exception {
        states.withStates().initial(OrderState.ORDER_WAIT_PAY).states(EnumSet.allOf(OrderState.class));
    }

    //状态转化方法
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeAction> transitions) throws Exception {
        transitions
                .withExternal()
                //订单支付Action(wait_pay->wait_send)
                .source(OrderState.ORDER_WAIT_PAY)
                .target(OrderState.ORDER_WAIT_SEND)
                .event(OrderStateChangeAction.PAY_ORDER)
                .and()
                .withExternal()
                //订单发货Action(wait_send->wait_receive)
                .source(OrderState.ORDER_WAIT_SEND)
                .target(OrderState.ORDER_WAIT_RECEIVE)
                .event(OrderStateChangeAction.SEND_ORDER)
                .and()
                .withExternal()
                //订单完成Action(wait_receive->finish)
                .source(OrderState.ORDER_WAIT_RECEIVE)
                .target(OrderState.ORDER_FINISH)
                .event(OrderStateChangeAction.RECEIVE_ORDER);
    }


}