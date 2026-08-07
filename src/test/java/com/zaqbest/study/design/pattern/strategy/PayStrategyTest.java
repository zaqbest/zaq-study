package com.zaqbest.study.design.pattern.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 策略模式测试
 *
 * @author ZAQ
 */
@SpringBootTest
class PayStrategyTest {

    @Autowired
    private PayStrategyFactory payStrategyFactory;

    @Test
    void testAliPay() {
        PayStrategy strategy = payStrategyFactory.getStrategy("alipay");
        String result = strategy.pay(100.0);
        System.out.println(result);
        assertEquals("使用支付宝支付了 100.0 元", result);
    }

    @Test
    void testWeChatPay() {
        PayStrategy strategy = payStrategyFactory.getStrategy("wechat");
        String result = strategy.pay(200.0);
        System.out.println(result);
        assertEquals("使用微信支付了 200.0 元", result);
    }

    @Test
    void testUnsupportedPayType() {
        assertThrows(IllegalArgumentException.class, () -> {
            payStrategyFactory.getStrategy("unknown");
        });
    }
}