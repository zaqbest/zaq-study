package com.zaqbest.study.atguigu_great_offer;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=16m
 *
 * java 8及之后的版本使用metaspace来代替永久代
 *
 * Meataspace是方法区在Hotspotz中的实现，它与持久代最大的区别在于
 * ：Meataspace并不在虚拟机内存中而是在本地内存中。也就是说，java8中，
 * class metadata(the virtual machines internal presentation of java class),
 * 被存储在Metaspace的native memory中
 *
 * 永久代（java8后被metaspace取代了），存放了以下信息：
 *
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 *
 * 模拟Meataspace空间溢出，我们不断生成类网元空间灌，类占据的空间总会超过Meataspace指定的空间大小
 *
 */
public class MetaspaceOOMDemo {
    static class OOMTest{ }

    public static void main(String[] args) {
        int i = 0; //模拟技术多少次后发生异常

        try{
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, args));
                enhancer.create();
            }
        } catch (Throwable e){
            System.out.println("******多少次后发生了异常"+i);
            e.printStackTrace();

        }
    }
}
