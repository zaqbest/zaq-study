package com.zaqbest.study.design.pattern.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 支付策略工厂
 *
 * @author ZAQ
 */
@Component
public class PayStrategyFactory {

    private final Map<String, PayStrategy> strategyMap;

    @Autowired
    public PayStrategyFactory(List<PayStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(PayStrategy::getPayType, Function.identity()));
    }

    /**
     * 根据支付方式获取策略
     *
     * @param payType 支付方式
     * @return 支付策略
     */
    public PayStrategy getStrategy(String payType) {
        PayStrategy strategy = strategyMap.get(payType);
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的支付方式: " + payType);
        }
        return strategy;
    }
}