package com.persagy.yc.common.model.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据类型常量
 *
 * @author Sun
 * @version 1.0 2014年9月24日
 * @since 1.6
 */
public enum DataType {

    /** 未知数据类型 */
    UNKNOWN("未知类型"),

    /** 字符串数据类型 */
    STRING("字符串"),

    /** 字节数据类型 */
    BYTE("字节"),

    /** 短整数类型 */
    SHORT("短整数"),

    /** 整数类型 */
    INT("整数"),

    /** 长整数类型 */
    LONG("长整数"),

    /** 浮点类型 */
    FLOAT("浮点"),

    /** 双精度浮点类型 */
    DOUBLE("双精度浮点"),

    /** 大数字类型 */
    BIGDECIMAL("大数字"),

    /** 布尔类型 */
    BOOLEAN("布尔"),

    /** 日期类型 */
    DATE("日期"),

    /** 时间类型 */
    TIME("时间"),

    /** 日期时间类型 */
    DATETIME("日期时间"),

    /** 二进制类型类型 */
    BINARY("二进制类型");

    /** 类型名称 */
    private String name;

    /**
     * 构造方法
     * @param name 名称
     */
    private DataType(String name) {
        this.name = name;
    }

    /**
     * 取得类型名称
     * @return 类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 判断是否为数字类型
     * @param dataType 数据类型
     * @return 布尔值
     */
    public static boolean isNumberType(DataType dataType) {
        return dataType == BYTE || dataType == SHORT || dataType == INT || dataType == LONG || dataType == FLOAT || dataType == DOUBLE || dataType == BIGDECIMAL;
    }

    /**
     * 判断是否是时间类型
     * @param dataType 数据类型
     * @return 布尔值
     */
    public static boolean isDateType(DataType dataType) {
        return dataType == DATE || dataType == DATETIME || dataType == TIME;
    }

    /**
     * 判断类是否为值类型
     * @param clazz 类对象
     * @return 布尔值
     */
    public static boolean isBaseDataType(Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class) || (Number.class).isAssignableFrom(clazz) || (Boolean.class).isAssignableFrom(clazz) || (Date.class).isAssignableFrom(clazz);
    }

    /**
     * 根据类对象取得对应的整型数据类型定义
     * @param clazz 类对象
     * @return 对应的数据类型
     */
    public static DataType parse(Class<?> clazz) {
        if (clazz == null) {
            return UNKNOWN;
        }
        if (clazz.equals(String.class)) {
            return STRING;
        }
        if (clazz.equals(Integer.TYPE)) {
            return INT;
        }
        if (clazz.equals(Integer.class)) {
            return INT;
        }
        if (clazz.equals(Long.TYPE)) {
            return LONG;
        }
        if (clazz.equals(Long.class)) {
            return LONG;
        }
        if (clazz.equals(BigDecimal.class)) {
            return BIGDECIMAL;
        }
        if (clazz.equals(Float.TYPE)) {
            return FLOAT;
        }
        if (clazz.equals(Float.class)) {
            return FLOAT;
        }
        if (clazz.equals(Double.TYPE)) {
            return DOUBLE;
        }
        if (clazz.equals(Double.class)) {
            return DOUBLE;
        }
        if (clazz.equals(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (clazz.equals(Boolean.class)) {
            return BOOLEAN;
        }
        if (clazz.equals(Date.class)) {
            return DATE;
        }
        if (clazz.equals(java.sql.Date.class)) {
            return DATE;
        }
        if (clazz.equals(java.sql.Time.class)) {
            return TIME;
        }
        if (clazz.equals(java.sql.Timestamp.class)) {
            return DATETIME;
        }
        if (clazz.equals(Byte.TYPE)) {
            return BYTE;
        }
        if (clazz.equals(Byte.class)) {
            return BYTE;
        }
        if (clazz.equals(Short.TYPE)) {
            return SHORT;
        }
        if (clazz.equals(Short.class)) {
            return SHORT;
        }
        return UNKNOWN;
    }

    /**
     * 数据类型名称转换成数据类型整数
     * @param type 数据类型名称
     * @return 数据类型整数
     */
    public static DataType parse(String type) {
        if (type == null) {
            return UNKNOWN;
        }
        try {
            type = type.toUpperCase();
            if ("INTEGER".equals(type)) {
                return INT;
            }
            return DataType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

    /**
     * 数据类型转换成对应的类对象
     * @param type 数据类型整数
     * @return 类对象
     */
    public static Class<?> typeToClass(DataType type) {
        switch (type) {
            case STRING:
                return String.class;
            case INT:
                return Integer.class;
            case LONG:
                return Long.class;
            case BIGDECIMAL:
                return BigDecimal.class;
            case BOOLEAN:
                return Boolean.class;
            case FLOAT:
                return Float.class;
            case DOUBLE:
                return Double.class;
            case DATE:
            case TIME:
            case DATETIME:
                return Date.class;
            case BYTE:
                return Byte.class;
            case SHORT:
                return Short.class;
            default:
                return null;
        }
    }
}
