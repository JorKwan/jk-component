package com.persagy.fm.mybatis.aop;

import cn.hutool.core.util.StrUtil;
import com.persagy.fm.mybatis.utils.MyBatisStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 格式化mybatis请求参数切面
 *
 * @author lixing
 * @version V1.0 2021/4/23 4:00 下午
 **/
@Component
@Aspect
@Slf4j
public class FormatMybatisQueryParamsAspect {
    @Pointcut("@annotation(com.persagy.fm.mybatis.annotations.FormatMybatisQueryParams)")
    private void pointCutMethod() {
    }

    @Before("pointCutMethod()")
    public void process(JoinPoint point) throws Throwable {
        // 获取方法的参数
        Object[] args = point.getArgs();
        for (Object arg : args) {
            // 获取方法入参的属性
            Field[] declaredFields = arg.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.getType().equals(String.class)) {
                    Method getMethod = arg.getClass().getDeclaredMethod(
                            "get" + MyBatisStringUtils.firstCharUpperCase(declaredField.getName()));
                    String s = (String) getMethod.invoke(arg);
                    s = formatUnderLine(s);
                    Method setMethod = arg.getClass().getDeclaredMethod(
                            "set" + MyBatisStringUtils.firstCharUpperCase(declaredField.getName()), String.class);
                    setMethod.invoke(arg, s);
                }
            }
        }
    }

    /**
     * 格式化下划线，如果字符串中包含下划线，将下划线替换为"\_"
     * mysql中下划线代表任意字符
     *
     * @param s 格式化前的字符串
     * @return 格式化后的字符串
     * @author lixing
     * @version V1.0 2021/4/23 4:14 下午
     */
    private String formatUnderLine(String s) {
        if (StrUtil.isBlank(s)) {
            return s;
        }
        if (s.contains("_")) {
            s = s.replaceAll("_", "\\\\_");
        }
        return s;
    }
}
