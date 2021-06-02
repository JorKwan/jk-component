package com.persagy.fm.mybatis.handler;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.Assert;
/***
 * Description: 针对mysql中json类型与Model映射处理器.用于处理集合的泛型不是String类型的
 * @author : lijie
 * @date :2021/4/27 15:18
 * Update By lijie 2021/4/27 15:18
 */
@Slf4j
@MappedTypes({ Object.class })
@MappedJdbcTypes(JdbcType.VARCHAR)
public class DbJsonTypeHandler extends AbstractJsonTypeHandler<Object> {

    private final Class<?> clazz;

    public DbJsonTypeHandler(Class<?> type, Class<?> innerType) {
        if (log.isTraceEnabled()) {
            log.trace("JacksonTypeHandler(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null");

        this.clazz = innerType;
    }

    @Override
    protected Object parse(String json) {
        if (JSONUtil.isJsonArray(json)) {
            return JSONUtil.toList(JSONUtil.parseArray(json), this.clazz);
        } else {
            return JSONUtil.toBean(json, this.clazz);
        }
    }

    @Override
    protected String toJson(Object obj) {
        return JSONUtil.toJsonStr(obj);
    }

}
