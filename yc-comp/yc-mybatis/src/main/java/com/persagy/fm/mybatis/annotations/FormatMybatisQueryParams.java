package com.persagy.fm.mybatis.annotations;

import java.lang.annotation.*;

/**
 * mybatis查询参数格式化注解
 *
 * @author lixing
 * @version V1.0 2021/4/23 3:58 下午
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface FormatMybatisQueryParams {
}
