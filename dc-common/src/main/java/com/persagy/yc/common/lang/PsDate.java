package com.persagy.yc.common.lang;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * 自定日期类，用于转化表示日期串，日期对象等
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
public class PsDate implements Serializable, Comparable<PsDate>, Cloneable {

    /** 序列id */
    private static final long serialVersionUID = 5668159214884319312L;

    /** 默认的日期格式化对象 */
    public static DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /** 默认时区 */
    public static ZoneId DEFAULT_ZONE_ID = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));

    /*日期时间常量*/
    /** 一天毫秒数 */
    public static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    /** 一小时毫秒数 */
    public static final int MILLIS_PER_HOUR = 60 * 60 * 1000;
    /** 一分钟毫秒数 */
    public static final int MILLIS_PER_MINUTE = 60 * 1000;
    /** 一秒毫秒数 */
    public static final int MILLIS_PER_SECOND = 1000;

    /** 保存时间的日期对象 */
    protected Calendar calendar = null;

    /**
     * 自定义日期无参构造方法。
     */
    public PsDate() {
        calendar = Calendar.getInstance();
        init();
    }

    /**
     * 通过毫秒数来构造自定义日期对象
     * (以从1970年1月1日0时0分0秒到现在的毫秒数来构造日期)
     * @param millis 毫秒数
     */
    public PsDate(long millis) {
        this();
        setMillis(millis);
    }

    /**
     * 通过日期对象构造自定义日期对象
     * @param date 日期对象
     */
    public PsDate(Date date) {
        this();
        setDate(date);
    }

    /**
     * 通过默认形式日期串构造自定义日期对象
     * @param date 默认格式日期串
     */
    public PsDate(String date) {
        this(date, DEFAULT_FORMAT);
    }

    /**
     * 通过默认形式日期串构造自定义日期对象
     * @param date   默认格式日期串
     * @param format 格式化串
     */
    public PsDate(String date, String format) {
        this();
        DateTimeFormatter dateFormat = null;
        if (format != null) {
            dateFormat = DateTimeFormatter.ofPattern(format);
        }
        parse(date, dateFormat);
    }

    /**
     * 通过日期串和指定的格式化对象构造自定义日期对象
     * @param date       符合格式化对象的日期时间对象
     * @param dateFormat 格式化对象
     */
    public PsDate(String date, DateTimeFormatter dateFormat) {
        this();
        parse(date, dateFormat);
    }

    /**
     * 按指定日期形式解析日期字符串
     * @param date       日期字符串
     * @param dateFormat 日期形式
     */
    protected void parse(String date, DateTimeFormatter dateFormat) {
        if (StrUtil.isEmpty(date)) {
            return;
        }
        if (dateFormat == null) {
            dateFormat = DEFAULT_FORMAT;
        }
        LocalDate localDate = LocalDate.parse(date, dateFormat);
        setDate(Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC)));
    }

    /**
     * 设置指定时间属性的值
     * @param field 时间属性
     * @param value 时间属性的值
     */
    public void set(int field, int value) {
        calendar.set(field, value);
    }

    /**
     * 取得定时间属性的值
     * @param field 时间属性
     * @return 定时间属性的值
     */
    public int get(int field) {
        return calendar.get(field);
    }

    /**
     * 初始方法
     */
    protected void init() {
        set(Calendar.HOUR_OF_DAY, 0);
        set(Calendar.MINUTE, 0);
        set(Calendar.SECOND, 0);
        set(Calendar.MILLISECOND, 0);
    }

    /**
     * 根据对象构建自定义日期对象
     * @param obj 对象
     * @return 自定义日期对象
     */
    public static PsDate parsePsDate(Object obj) {
        if (obj == null || "".equals(obj.toString().trim())) {
            return null;
        }
        if (obj instanceof PsDate) {
            return new PsDate(((PsDate) obj).getMillis());
        }

        if (obj instanceof Date) {
            return new PsDate((Date) obj);
        }
        if (obj instanceof Integer) {
            return new PsDate(((Number) obj).longValue() * 1000);
        }
        if (obj instanceof Number) {
            return new PsDate(((Number) obj).longValue());
        }
        // 目前存储的格式，都是数字，兼容时间戳会误判，暂不这样处理了
//        if (NumberUtils.isCreatable(obj.toString())) {
//            return new PsDate(NumberUtils.toLong(obj.toString()));
//        }
        return new PsDate(obj.toString());

    }

    /**
     * 设置日期对象的时间
     * @param date 日期对象
     */
    public void setDate(Date date) {
        calendar.setTime(date);
        init();
    }

    /**
     * 取得当前日期所表示的java日期对象
     * @return java日期对象
     */
    public Date getDate() {
        return calendar.getTime();
    }

    /**
     * 设置日期对象的时间
     * @param millis 日期对象
     */
    public void setMillis(long millis) {
        calendar.setTimeInMillis(millis);
        init();
    }

    /**
     * 取得日期对象的毫秒数
     * @return 毫秒数
     */
    public long getMillis() {
        return calendar.getTimeInMillis();
    }

    /**
     * 比较日期先后，对象日期在参数日期之后为true
     * @param when 比较的日期的对象
     * @return 布尔值
     */
    public boolean after(PsDate when) {
        return compareTo(when) > 0;
    }

    /**
     * 比较日期先后，对象日期在参数日期之前为true
     * @param when 比较的日期的对象
     * @return 布尔值
     */
    public boolean before(PsDate when) {
        return compareTo(when) < 0;
    }

    /**
     * 返回指定天数之后的自定义日期对象
     * @param days 指定的天数
     * @return 自定义日期对象
     */
    public PsDate getDateAfter(int days) {
        long millis = getMillis() + MILLIS_PER_DAY * days;
        return new PsDate(millis);
    }

    /**
     * 返回指定天数之前的自定义日期对象
     * @param days 指定的天数
     * @return 自定义日期对象
     */
    public PsDate getDateBefore(int days) {
        return getDateAfter(-days);
    }

    /**
     * 取得当前日期对象在指定的日期对象之后多少天
     * @param when 要比较的自定义对象
     * @return 在比较的自定义对象之后多少天
     */
    public int getDaysAfter(PsDate when) {
        return getDaysBetween(when, this);
    }

    /**
     * 取得开始日期到结束日期直接相差多少天
     * @param begin 开始日期
     * @param end   结束日期
     * @return 相差天数
     */
    public static int getDaysBetween(PsDate begin, PsDate end) {
        int days = 0;
        if (begin != null && end != null) {
            PsDate e = new PsDate(end.getMillis());
            PsDate b = new PsDate(begin.getMillis());
            days = (int) (e.getMillis() / MILLIS_PER_DAY - b.getMillis() / MILLIS_PER_DAY);
        }
        return days;
    }

    /**
     * 格式化时间数字串为两位
     * @param number 数字
     * @return 数字串
     */
    public String formatNumber(int number) {
        return number < 10 ? "0" + number : Integer.toString(number);
    }

    /**
     * 取得日期对象中的日
     * @return 天数
     */
    public int getDay() {
        return get(Calendar.DATE);
    }

    /**
     * 取得日期对象中的日串
     * @return 天数串（两位）
     */
    public String getDAY() {
        return formatNumber(getDay());
    }

    /**
     * 取得日期对象的月份
     * @return
     */
    public int getMonth() {
        return get(Calendar.MONTH);
    }

    /**
     * 取得日期对象的月份串（两位）
     * @return 月份串（两位）
     */
    public String getMONTH() {
        return formatNumber(getMonth() + 1);
    }

    /**
     * 取得当前日期对象所在月的英文写法
     * @return 月的英文串
     */
    public String getEnMonth() {
        switch (getMonth()) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return null;
        }
    }

    /**
     * 取得日期对象的年份
     * @return 年份串
     */
    public int getYear() {
        return get(Calendar.YEAR);
    }

    /**
     * 取得年字符串
     * @return 年字符串
     */
    public String getYEAR() {
        return Integer.toString(getYear());
    }


    /**
     * 取得当前日期对象的星期表示
     * @return 星期表示
     */
    public int getWeek() {
        return get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 取得当前日期对象的星期中文简单表示
     * @return 星期中文简单表示
     */
    public String getSimpleZhWeek() {
        switch (getWeek()) {
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
            default:
                return null;
        }
    }

    /**
     * 取得当前日期对象的星期英文表示
     * @return 星期英文表示
     */
    public String getZhWeek() {
        switch (getWeek()) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return null;
        }
    }

    /**
     * 取得当前日期对象的星期英文表示
     * @return 星期英文表示
     */
    public String getEnWeek() {
        switch (getWeek()) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return null;
        }
    }

    /**
     * 取得当前日期对象的星期英文简单表示
     * @return 星期英文简单表示
     */
    public String getSimpleEnWeek() {
        switch (getWeek()) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
            default:
                return null;
        }
    }


    /**
     * 判断当前日期所在是否是闰年
     * @return 布尔值
     */
    public boolean isLeapYear() {
        return isLeapYear(getYear());
    }

    /**
     * 判断指定年是否是闰年
     * @param year 　指定的年
     * @return　布尔值
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * 取得当前日期对象所在月有多少天
     * @return 天数
     */
    public int getDaysInMonth() {
        return getDaysMonth(getYear(), getMonth());
    }

    /**
     * 取得指定的年月里一个月有多少天
     * @param year  指定的年
     * @param month 指定的月
     * @return 天数
     */
    public static int getDaysMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;
        }
    }

    /**
     * 取得当前日期所在年包含的周数
     * @return　年包含的周数
     */
    public int getWeekOfYear() {
        return get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 用默认的格式化串转化日期
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return toString(DEFAULT_FORMAT);
    }

    /**
     * 用指定的格式化串，格式化日期对象为日期串
     * @param format 格式化串
     * @return　格式化后的日期串
     */
    public String toString(String format) {
        if (format == null) {
            return toString();
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        return toString(dateFormat);
    }

    /**
     * 用指定的格式化对象，格式化日期对象为日期串
     * @param dateFormat 指定的格式化对象
     * @return　格式化后的日期串
     */
    public String toString(DateTimeFormatter dateFormat) {
        if (dateFormat == null) {
            return toString();
        }
        return dateFormat.format(getDate().toInstant().atZone(PsDate.DEFAULT_ZONE_ID).toLocalDate());
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
     * 克隆日期对象。
     * @see Object#clone()
     */
    @Override
    public Object clone() {
        PsDate psDate;
        try {
            psDate = (PsDate) super.clone();
        } catch (CloneNotSupportedException e) {
            return new PsDate(getMillis());
        }
        psDate.calendar = (Calendar) calendar.clone();
        return psDate;
    }

    /**
     * 取得对象的hashCode
     * @see Date#hashCode()
     */
    @Override
    public int hashCode() {
        return calendar.hashCode();
    }

}