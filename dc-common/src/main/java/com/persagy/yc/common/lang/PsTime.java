package com.persagy.yc.common.lang;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * 自定时间类，用于转化表示时间串等
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
public class PsTime extends PsDate {
    /** 序列化id */
    private static final long serialVersionUID = 733417362967334525L;

    /** 时间格式化对象 */
    public static DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("HHmmss");

    /**
     * 自定义时间无参构造方法。
     */
    public PsTime() {
        super();
    }

    /**
     * 通过毫秒数构造自定义时间对象
     * (以从1970年1月1日0时0分0秒到现在的毫秒数来构造日期)
     * @param millis 毫秒数
     */
    public PsTime(long millis) {
        super(millis);
    }

    /**
     * 通过日期对象构造自定义时间对象
     *
     * @param date 日期对象
     */
    public PsTime(Date date) {
        super(date);
    }

    /**
     * 通过默认形式时间串构造自定义时间对象
     *
     * @param time 默认格式日期串
     */
    public PsTime(String time) {
        super(time, DEFAULT_FORMAT);
    }

    /**
     * 通过时间串和指定的格式化串构造自定义时间对象
     *
     * @param time   符合格式串的时间对象
     * @param format 时间格式化串
     */
    public PsTime(String time, String format) {
        super(time, format);
    }

    /**
     * 通过时间串和指定的格式化对象构造自定义时间对象
     *
     * @param time       符合格式化对象的时间对象
     * @param timeFormat 格式化对象
     */
    public PsTime(String time, DateTimeFormatter timeFormat) {
        super(time, timeFormat);
    }

    /**
     * 根据对象构建自定义时间对象
     * @param obj 对象
     * @return 自定义时间对象
     */
    public static PsTime parsePsTime(Object obj) {
        if (obj == null || "".equals(obj.toString().trim())) {
            return null;
        }
        if (obj instanceof PsDate) {
            return new PsTime(((PsDate) obj).getMillis());
        }

        if (obj instanceof Date) {
            return new PsTime((Date) obj);
        }
        if (obj instanceof Integer) {
            return new PsTime(((Integer) obj).longValue() * 1000);
        }
        if (obj instanceof Number) {
            return new PsTime(((Number) obj).longValue());
        }
//        if (NumberUtils.isCreatable(obj.toString())) {
//            return new PsTime(NumberUtils.toLong(obj.toString()));
//        }
        return new PsTime(obj.toString());
    }

    /**
     * 用时间对象初始化对象
     */
    @Override
    protected void init() {
        set(Calendar.YEAR, 1970);
        set(Calendar.MONTH, 1);
        set(Calendar.DATE, 1);
    }

    @Override
    protected void parse(String date, DateTimeFormatter dateFormat) {
        if (StrUtil.isEmpty(date)) {
            return;
        }
        if (dateFormat == null) {
            dateFormat = DEFAULT_FORMAT;
        }
        LocalTime localDate = LocalTime.parse(date, dateFormat);
        ZonedDateTime zdt = localDate.atDate(LocalDate.now()).atZone(PsDate.DEFAULT_ZONE_ID);
        setDate(Date.from(zdt.toInstant()));
    }

    /**
     * 比较时间先后，对象时间在参数时间之后为true
     * @param when 比较的时间对象
     * @return 布尔值
     */
    public boolean after(PsTime when) {
        return compareTo(when) > 0;
    }

    /**
     * 比较时间先后，对象时间在参数时间之前为true
     * @param when 比较的时间对象
     * @return 布尔值
     */
    public boolean before(PsTime when) {
        return compareTo(when) < 0;
    }

    /**
     * 比较日期先后，对象日期在参数日期之后为true
     * @param when 比较的日期的对象
     * @return 布尔值
     * @deprecated
     */
    @Override
    public boolean after(PsDate when) {
        return getMillis() > 0;
    }

    /**
     * 比较日期先后，对象日期在参数日期之前为true
     * @param when 比较的日期的对象
     * @return 布尔值
     * @deprecated
     */
    @Override
    public boolean before(PsDate when) {
        return getMillis() < 0;
    }

    /**
     * 返回指定天数之后的自定义日期对象
     * @param days 指定的天数
     * @return 自定义日期对象
     * @deprecated
     */
    @Override
    public PsDate getDateAfter(int days) {
        return null;
    }

    /**
     * 返回指定天数之前的自定义日期对象
     * @param days 指定的天数
     * @return 自定义日期对象
     * @deprecated
     */
    @Override
    public PsDate getDateBefore(int days) {
        return getDateAfter(-days);
    }

    /**
     * 取得当前日期对象在指定的日期对象之后多少天
     * @param when 要比较的自定义对象
     * @return 在比较的自定义对象之后多少天
     * @deprecated
     */
    @Override
    public int getDaysAfter(PsDate when) {
        return 0;
    }

    /**
     * 取得两个时间对象相差的小时数
     * @param begin 开始时间
     * @param end   结束时间
     * @return 相差的小时整数
     */
    public static int getHoursBetween(PsTime begin, PsTime end) {
        return (int) (getMilisBetween(begin, end) / MILLIS_PER_HOUR);
    }

    /**
     * 取得两个时间对象相差的分钟数
     * @param begin 开始时间
     * @param end   结束时间
     * @return 相差的分钟整数
     */
    public static int getMinutesBetween(PsTime begin, PsTime end) {
        return (int) (getMilisBetween(begin, end) / MILLIS_PER_MINUTE);
    }

    /**
     * 取得两个时间对象相差的秒数
     * @param begin 开始时间
     * @param end   结束时间
     * @return 相差的秒整数
     */
    public static int getSecondsBetween(PsTime begin, PsTime end) {
        return (int) (getMilisBetween(begin, end) / MILLIS_PER_SECOND);
    }

    /**
     * 取得两个时间对象相差的毫秒数
     * @param begin 开始时间
     * @param end   结束时间
     * @return 相差的毫秒整数
     */
    public static long getMilisBetween(PsTime begin, PsTime end) {
        if (begin == null || end == null) {
            return 0;
        }
        return end.getMillis() - begin.getMillis();
    }

    /**
     * 取得当前时间对象在指定的时间对象之后多少毫秒
     * @param time 要比较的自定义时间
     * @return 当前时间在给定时间
     */
    public long getMillisAfter(PsTime time) {
        return getMilisBetween(time, this);
    }

    /**
     * 用默认的格式化串转化日期
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return toString(DEFAULT_FORMAT);
    }

    @Override
    public String toString(DateTimeFormatter dateFormat) {
        if (dateFormat == null) {
            return toString();
        }
        return dateFormat.format(getDate().toInstant().atZone(PsDate.DEFAULT_ZONE_ID).toLocalTime());
    }
}
