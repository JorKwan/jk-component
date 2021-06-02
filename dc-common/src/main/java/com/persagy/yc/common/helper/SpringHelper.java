package com.persagy.yc.common.helper;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.persagy.yc.common.utils.TypeConvertUtil;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Spring助手
 * @author Charlie Yu
 * @version 1.0 2021-03-02
 */
public class SpringHelper {

    /**
     * spring上下文
     * @return spring上下文
     */
    public static ApplicationContext getContext(){
        return SpringUtil.getApplicationContext();
    }

    /**
     * 根据类名搜索相应的bean对象
     * @param <T> 类模板标识返回值类型
     * @param clazz 模板类
     * @return bean对象
     */
    public static <T>T getBean(Class<T> clazz){
        return SpringUtil.getBean(clazz);
    }

    /**
     * 根据类型取得所有
     * @param type 接口类
     * @return 取得所有的实现bean
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type){
        return SpringUtil.getBeansOfType(type);
    }

    /**
     * 根据bean名称和bean类型搜索相应的bean对象
     * @param <T> 类模板标识返回值类型
     * @param beanName bean名称
     * @param clazz 模板类
     * @return bean对象
     */
    public static <T>T getBean(String beanName,Class<T> clazz){
        return SpringUtil.getBean(beanName, clazz);
    }

    /**
     * 根据bean名称搜索相应的bean对象
     * @param beanName bean名称
     * @return bean对象
     */
    public static <T>T getBean(String beanName){
        return SpringUtil.getBean(beanName);
    }

    /**
     * 根据配置名称取得字符串配置值（配置名形式为nodeName.nodeName）
     * @param key 配置名称
     * @return 指定配置字符串值
     */
    public static String getString(String key) {
        return SpringUtil.getProperty(key);
    }

    /**
     * 根据配置名称取得字符串配置值，如果配置值为空使用指定的默认值
     * @param key         配置名称
     * @param defaultValue 指定默认值
     * @return 最终配置值
     */
    public static String getString(String key, String defaultValue) {
        String value = getString(key);
        return StrUtil.isBlank(value) ? defaultValue : value;
    }

    /**
     * 根据配置名称取得整型配置值，如果配置值为空使用指定的默认值
     * @param key         配置名称
     * @param defaultValue 默认整型值
     * @return 最终配置值
     */
    public static int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (StrUtil.isEmpty(value)) {
            return defaultValue;
        }
        return TypeConvertUtil.parseInt(value);
    }
    /**
     * 根据配置名称取得布尔配置值，如果配置值为空使用指定的默认值
     * @param key         配置名称
     * @param defaultValue 默认布尔值
     * @return 最终配置值
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key);
        if (StrUtil.isEmpty(value)) {
            return defaultValue;
        }
        return TypeConvertUtil.parseBoolean(value);
    }

    /**
     * 根据配置名称取得长整型配置值，如果配置值为空使用指定的默认值
     * @param key         配置名称
     * @param defaultValue 默认长整型值
     * @return 最终配置值
     */
    public static long getLong(String key, long defaultValue) {
        String value = getString(key);
        if (StrUtil.isEmpty(value)) {
            return defaultValue;
        }
        return TypeConvertUtil.parseLong(value);
    }

    /**
     * 根据配置名称取得浮点型配置值，如果配置值为空使用指定的默认值
     * @param key         配置名称
     * @param defaultValue 默认浮点型值
     * @return 最终配置值
     */
    public static float getFloat(String key, float defaultValue) {
        String value = getString(key);
        if (StrUtil.isEmpty(value)) {
            return defaultValue;
        }
        return TypeConvertUtil.parseFloat(value);
    }

    /**
     * 根据配置名称取得双浮点型配置值，如果配置值为空使用指定的默认值
     * @param key         配置名称
     * @param defaultValue 默认双浮点型值
     * @return 最终配置值
     */
    public static double getDouble(String key, double defaultValue) {
        String value = getString(key);
        if (StrUtil.isEmpty(value)) {
            return defaultValue;
        }
        return TypeConvertUtil.parseDouble(value);
    }

}
