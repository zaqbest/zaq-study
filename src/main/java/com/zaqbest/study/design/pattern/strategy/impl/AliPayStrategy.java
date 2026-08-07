package com.zaqbest.study.design.pattern.strategy.impl;

import com.zaqbest.study.design.pattern.strategy.PayStrategy;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付策略
 *
 * @author ZAQ
 */
@Component
public class AliPayStrategy implements PayStrategy {

    @Override
    public String pay(double amount) {
        return "使用支付宝支付了 " + amount + " 元";
    }

    @Override
    public String getPayType() {
        return "alipay";
    }
}