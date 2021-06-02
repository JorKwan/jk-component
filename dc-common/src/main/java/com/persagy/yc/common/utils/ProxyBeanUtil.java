package com.persagy.yc.common.utils;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * 动态bean处理工具类
 * @author Charlie Yu
 * @version 1.0 2021-03-03
 */
public class ProxyBeanUtil {

    /**
     * 创建Bean生成器
     * @param source 原始Bean
     * @param properties 附加属性
     * @return Bean生成器
     */
    public static BeanGenerator createBeanGenerator(Object source, String... properties) {
        BeanMap sourceMap = BeanMap.create(source);
        return createBeanGenerator(sourceMap,properties);
    }

    /**
     * 创建Bean生成器
     * @param source 原始Bean
     * @param propertyMap 附加属性
     * @return Bean生成器
     */
    public static BeanGenerator createBeanGenerator(Object source, Map<String, Class<?>> propertyMap) {
        BeanMap sourceMap = BeanMap.create(source);
        return createBeanGenerator(sourceMap,propertyMap);
    }

    /**
     * 创建Bean生成器
     * @param sourceMap 原始Bean对应beanMap
     * @param properties 附加属性
     * @return Bean生成器
     */
    public static BeanGenerator  createBeanGenerator(BeanMap sourceMap, String... properties) {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(sourceMap.getBean().getClass());
        int count = 0;
        for (String prop : properties) {
            if(sourceMap.containsKey(prop)) {
                continue;
            }
            generator.addProperty(prop,String.class);
            count++;
        }
        if(count  == 0) {
            return null;
        }
        return generator;
    }

    /**
     * 创建Bean生成器
     * @param sourceMap 原始Bean对应beanMap
     * @param propertyMap 附加属性
     * @return Bean生成器
     */
    public static BeanGenerator  createBeanGenerator(BeanMap sourceMap, Map<String, Class<?>> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(sourceMap.getBean().getClass());
        int count = 0;
        for (Map.Entry<String,Class<?>> entry:propertyMap.entrySet()) {
            String key = entry.getKey();
            if (sourceMap.containsKey(key)) {
                continue;
            }
            generator.addProperty(key, entry.getValue());
            count++;
        }
        if(count  == 0) {
            return null;
        }
        return generator;
    }

    /**
     * 创建继承于原始Bean的新对象
     * @param source 原始Bean
     * @param properties 附加属性
     * @return 新对象
     */
    public static <X> X  createProxyBean(X source, String... properties) {
        BeanGenerator generator = createBeanGenerator(source,properties);
        if(generator == null) {
            return source;
        }
        return createProxyBean(generator,source);
    }

    /**
     * 创建继承于原始Bean的新对象
     * @param source 原始Bean
     * @param propertyMap 附加属性
     * @return 新对象
     */
    public static <X> X  createProxyBean(X source, Map<String, Class<?>> propertyMap) {
        BeanGenerator generator = createBeanGenerator(source,propertyMap);
        if(generator == null) {
            return source;
        }
        return createProxyBean(generator,source);
    }

    /**
     * 抽取的公共方法
     * @param generator 新的bean生成器
     * @param source 复制扩展源对象
     * @param <X> 源类型
     * @return 新对象
     */
    private static <X> X createProxyBean(BeanGenerator generator,X source){
        BeanMap sourceMap = BeanMap.create(source);
        @SuppressWarnings("unchecked")
        X target = (X) generator.create();
        BeanMap targetMap = BeanMap.create(target);
        targetMap.putAll(sourceMap);
        return target;
    }
}