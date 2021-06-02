package com.persagy.yc.common.lang;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * 自定日期时间类，用于转化表示日期时间串等
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
public class PsDateTime extends PsDate implements Comparable<PsDate> {
    /** 序列化id */
    private static final long serialVersionUID = 7280791980952901691L;

    /** 日期格式化对象 */
    public static DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /** 自定义日期时间无参构造方法。 */
    public PsDateTime() {
        super();
    }

    /**
     * 通过毫秒数构造自定义日期时间对象
     * (以从1970年1月1日0时0分0秒到现在的毫秒数来构造日期)
     * @param millis 毫秒数
     */
    public PsDateTime(long millis) {
        super(millis);
    }

    /**
     * 通过日期对象构造自定义日期时间对象
     * @param date 日期对象
     */
    public PsDateTime(Date date) {
        super(date);
    }

    /**
     * 通过自定义日期对象构造自定义日期时间对象
     * @param date 日期对象
     */
    public PsDateTime(PsDate date) {
        super(date.getMillis());
    }

    /**
     * 通过自定义日期对象和时间对象构造自定义日期时间对象
     * @param date 日期对象
     */
    public PsDateTime(PsDate date, PsTime time) {
        super(date.getMillis() + time.getMillis());
    }

    /**
     * 通过默认形式日期串构造自定义日期对象
     * @param date 默认格式日期串
     */
    public PsDateTime(String date) {
        super(date, DEFAULT_FORMAT);
    }

    /**
     * 通过日期串和指定的格式化串构造自定义日期时间对象
     * @param date   符合格式串的日期时间对象
     * @param format 日期格式化串
     */
    public PsDateTime(String date, String format) {
        super(date, format);
    }

    /**
     * 通过日期串和指定的格式化对象构造自定义日期时间对象
     * @param date       符合格式化对象的日期时间对象
     * @param dateFormat 格式化对象
     */
    public PsDateTime(String date, DateTimeFormatter dateFormat) {
        super(date, dateFormat);
    }


    /**
     * 用日期对象初始化对象
     */
    @Override
    protected void init() {
        set(Calendar.MILLISECOND, 0);
    }

    @Override
    protected void parse(String date, DateTimeFormatter dateFormat) {
        if (StrUtil.isEmpty(date)) {
            return;
        }
        if (dateFormat == null) {
            dateFormat = DEFAULT_FORMAT;
        }
        LocalDateTime localDate = LocalDateTime.parse(date, dateFormat);
        ZonedDateTime zdt = localDate.atZone(PsDate.DEFAULT_ZONE_ID);
        setDate(Date.from(zdt.toInstant()));
    }

    /**
     * 根据对象构建自定义日期时间对象
     * @param obj 对象
     * @return 自定义日期时间对象
     */
    public static PsDateTime parsePsDateTime(Object obj) {
        if (obj == null || "".equals(obj.toString())) {
            return null;
        }
        if (obj instanceof PsDate) {
            return new PsDateTime(((PsDate) obj).getMillis());
        }

        if (obj instanceof Date) {
            return new PsDateTime((Date) obj);
        }
        if (obj instanceof Integer) {
            return new PsDateTime(((Integer) obj).longValue() * 1000);
        }
        if (obj instanceof Number) {
            return new PsDateTime(((Number) obj).longValue());
        }
//        if (NumberUtils.isCreatable(obj.toString())) {
//            return new PsDateTime(NumberUtils.toLong(obj.toString()));
//        }
        return new PsDateTime(obj.toString());

    }

    /**
     * 取得自定义日期时间对象中的自定义日期对象
     * @return 自定义日期对象
     */
    public PsDate getDDate() {
        return new PsDate(getMillis());
    }

    /**
     * 取得自定义日期时间对象中的自定义时间对象
     * @return 自定义时间对象
     */
    public PsTime getDTime() {
        return new PsTime(getMillis());
    }

    /**
     * 返回指定天数之后的自定义日期时间对象
     */
    @Override
    public PsDateTime getDateAfter(int days) {
        return new PsDateTime(getMillis() + MILLIS_PER_DAY * days);
    }

    /**
     * 返回指定天数之前的自定义日期时间对象
     */
    @Override
    public PsDateTime getDateBefore(int days) {
        return getDateAfter(-days);
    }

    /**
     * 取得两个日期时间对象相差的小时数
     * @param begin 开始日期时间
     * @param end   结束日期时间
     * @return 小时数
     */
    public static int getHoursBetween(PsDateTime begin, PsDateTime end) {
        return (int) (end.getMillis() / PsDate.MILLIS_PER_HOUR - begin.getMillis() / PsDate.MILLIS_PER_HOUR);
    }

    /**
     * 取得两个日期时间对象相差的分钟数
     * @param begin 开始日期时间
     * @param end   结束日期时间
     * @return 分钟数
     */
    public static int getMinutesBetween(PsDateTime begin, PsDateTime end) {
        return (int) (end.getMillis() / PsDate.MILLIS_PER_MINUTE - begin.getMillis() / PsDate.MILLIS_PER_MINUTE);
    }

    /**
     * 取得两个日期时间对象相差的秒数
     * @param begin 开始日期时间
     * @param end   结束日期时间
     * @return 秒数
     */
    public static int getSecondsBetween(PsDateTime begin, PsDateTime end) {
        return (int) (end.getMillis() / PsDate.MILLIS_PER_SECOND - begin.getMillis() / PsDate.MILLIS_PER_SECOND);
    }

    /**
     * 取得两个日期时间对象相差的毫秒数
     * @param begin 开始日期时间
     * @param end   结束日期时间
     * @return 毫秒数
     */
    private static long getMillisBetween(PsDateTime begin, PsDateTime end) {
        if (begin == null || end == null) {
            return 0;
        }
        return end.getMillis() - begin.getMillis();
    }

    /**
     * 取得当前日期时间对象在指定的日期时间对象之后多少毫秒
     * @param dateTime 要比较的自定义日期时间
     * @return 当前时间在给定日期时间
     */
    public long getMillisAfter(PsDateTime dateTime) {
        return getMillisBetween(dateTime, this);
    }

    /**
     * 取得小时数
     * @return 小时数
     */
    public int getHour() {
        return get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 取得小时数字符串
     * @return 小时数字符串
     */
    public String getHOUR() {
        return formatNumber(getHour());
    }

    /**
     * 取得分钟数
     * @return 分钟数
     */
    public int getMinute() {
        return get(Calendar.MINUTE);
    }

    /**
     * 取得分钟数字符串
     * @return 分钟数字符串
     */
    public String getMINUTE() {
        return formatNumber(getMinute());
    }

    /**
     * 取得秒数
     * @return 秒数
     */
    public int getSecond() {
        return get(Calendar.SECOND);
    }

    /**
     * 取得秒数字符串
     * @return 秒数字符串
     */
    public String getSECOND() {
        return formatNumber(getSecond());
    }

    /**
     * 取得毫秒数
     * @return 毫秒数
     */
    public int getMilliSecond() {
        return get(Calendar.MILLISECOND);
    }

    /**
     * 取得毫秒数串
     * @return 毫秒数串
     */
    public String getMILLISECOND() {
        int ms = getMilliSecond();
        return Integer.toString(ms + 1000).substring(1);
    }

    /**
     * 实现比较接口方法
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(PsDate psDate) {
        return calendar.compareTo(psDate.calendar);
    }

    /**
     * 比较日期是否相等
     * @param o 比较的对象
     * @return 布尔值
     */
    @Override
    public boolean equals(Object o) {
        if ((o != null) && (o instanceof PsDate)) {
            return this.getMillis() == ((PsDate) o).getMillis();
        }
        return false;
    }

    /**
     * 用默认的格式化串转化日期时间
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
        return dateFormat.format(getDate().toInstant().atZone(PsDate.DEFAULT_ZONE_ID));
    }
}