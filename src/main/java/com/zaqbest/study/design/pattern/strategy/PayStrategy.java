package com.zaqbest.study.design.pattern.strategy;

/**
 * 支付策略接口
 *
 * @author ZAQ
 */
public interface PayStrategy {

    /**
     * 支付
     *
     * @param amount 金额
     * @return 支付结果
     */
    String pay(double amount);

    /**
     * 获取支付方式名称
     *
     * @return 支付方式名称
     */
    String getPayType();
}