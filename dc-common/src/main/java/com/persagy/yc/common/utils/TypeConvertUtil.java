package com.persagy.yc.common.utils;

import cn.hutool.core.util.StrUtil;
import com.persagy.yc.common.model.data.DataType;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据类型转换器
 * @author Charlie Yu
 * @version 1.0 2021-03-03
 */
@Slf4j
public class TypeConvertUtil {

    /**
     * 私有构造方法，防止实例化
     */
    private TypeConvertUtil() {

    }

    /**
     * 将Object对象解析成字符串类型
     * @param obj 需要转换的对象
     * @return 字符串
     */
    public static final String parseString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 将对象解析成字节类型
     * @param obj 需要转换的对象
     * @return 字节值
     */
    public static final byte parseByte(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).byteValue();
        }
        if (obj instanceof Boolean) {
            return (byte) (((Boolean) obj).booleanValue() ? 1 : 0);
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return 0;
        } else {
            return Byte.parseByte(s);
        }
    }

    /**
     * 将对象解析成字节类型
     * @param obj 需要转换的对象
     * @return 字节值
     */
    public static final short parseShort(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).shortValue();
        }
        if (obj instanceof Boolean) {
            return (short) (((Boolean) obj).booleanValue() ? 1 : 0);
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return 0;
        } else {
            return Short.parseShort(s);
        }
    }

    /**
     * 将对象解析成整数类型
     * @param obj 需转换的对象
     * @return 整数值
     */
    public static final int parseInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? 0 : 1;
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    /**
     * 将对象解析成长整类型
     * @param obj 需转换的对象
     * @return 长整数值
     */
    public static final long parseLong(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? 0L : 1L;
        }
        if (obj instanceof Date) {
            return ((Date) obj).getTime();
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return 0;
        } else {
            return Long.parseLong(s);
        }
    }

    /**
     * 将对象解析成布尔类型数据
     * @param obj 需转换的对象
     * @return 布尔值
     */
    public static final boolean parseBoolean(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        } else {
            String s = obj.toString();
            return "true".equalsIgnoreCase(s) || "1".equals(s) || "Y".equalsIgnoreCase(s) || "YES".equalsIgnoreCase(s);
        }
    }

    /**
     * 将对象解析成浮点型数据
     * @param obj 需转换的对象
     * @return 浮点数值
     */
    public static final float parseFloat(Object obj) {
        if (obj == null) {
            return 0.0f;
        }
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? 0.0F : 1.0F;
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return 0.0f;
        } else {
            return Float.parseFloat(s);
        }
    }

    /**
     * 将对象解析成双浮点型数据
     * @param obj 需转换的对象
     * @return 双浮点值
     */
    public static final double parseDouble(Object obj) {
        if (obj == null) {
            return 0.0d;
        }
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? 0.0D : 1.0D;
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return 0.0d;
        } else {
            return Double.parseDouble(s);
        }
    }

    /**
     * 解析对象成大数字类型数据
     * @param obj 需要转换的对象
     * @return 大数字对象
     */
    public static final BigDecimal parseBigDecimal(Object obj) {
        if (obj == null) {
            return BigDecimal.valueOf(0L);
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof Number) {
            return BigDecimal.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return BigDecimal.valueOf(((Boolean) obj).booleanValue() ? 1L : 0L);
        }
        String s;
        if ("".equals(s = obj.toString())) {
            return BigDecimal.valueOf(0L);
        } else {
            return new BigDecimal(s);
        }
    }

    /**
     * 解析对象成日期数据类型
     *
     * @param obj 需转换的对象
     * @return 日期对象
     */
    public static Date parseDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
        }
        String objString = String.valueOf(obj);
        if (StrUtil.isBlank(objString)) {
            return null;
        }
        if (isNumeric(objString)) {
            long time = Long.parseLong(objString);
            return new Date(time);
        }
        int len = objString.length();
        if (len >= 19) {
            return null;
        }
        boolean isDate = objString.indexOf("-") > 0;
        boolean isTime = objString.indexOf(":") > 0;
        try {
            if (isDate && isTime) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objString);
            }
            if (isDate) {
                return new SimpleDateFormat("yyyy-MM-dd").parse(objString);
            }
            if (isTime) {
                return new SimpleDateFormat("HH:mm:ss").parse(objString);
            }
        } catch (ParseException e) {
            log.error("TypeConvert parse: Date parse error", e);
        }
        return null;
    }

    /**
     * 将未知类型的对象转换成指定类型的对象
     * @param clazz 数据类型
     * @param obj  未知类型对象
     * @return 目标类型对象
     */
    public static final Object translate(Class clazz, Object obj) {
        return translate(DataType.parse(clazz), obj);
    }

    /**
     * 将未知类型的对象转换成指定类型的对象
     * @param dataType 数据类型
     * @param obj      未知类型对象
     * @return 目标类型对象
     */
    public static final Object translate(DataType dataType, Object obj) {
        boolean isBlank = obj == null || (obj instanceof String) && ((String) obj).length() == 0;
        if (isBlank) {
            if (dataType == DataType.STRING) {
                return obj;
            } else {
                return null;
            }
        }
        switch (dataType) {
            case STRING:
                return parseString(obj);
            case INT:
                return parseInt(obj);
            case LONG:
                return parseLong(obj);
            case BIGDECIMAL:
                return parseBigDecimal(obj);
            case BOOLEAN:
                return parseBoolean(obj);
            case FLOAT:
                return parseFloat(obj);
            case DOUBLE:
                return parseDouble(obj);
            case DATE:
            case TIME:
            case DATETIME:
                return parseDate(obj);
            case BYTE:
                return parseByte(obj);
            case SHORT:
                return parseShort(obj);
            default:
                return obj;
        }
    }

    /**
     * 判断字符串是否是有效的数组
     * @param s 需要判断的字符串
     * @return 布尔值
     */
    public static boolean isNumeric(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            boolean isNumberChar = (c < '0' || c > '9') && c != '.' && (i != 0 || c != '-');
            if (isNumberChar) {
                return false;
            }
        }
        return true;
    }
}
