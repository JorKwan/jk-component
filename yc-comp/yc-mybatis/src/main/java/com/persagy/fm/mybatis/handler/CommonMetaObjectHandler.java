package com.persagy.fm.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.persagy.yc.common.constant.ValidEnum;
import com.persagy.yc.common.model.entity.AuditableEntity;
import com.persagy.yc.common.model.entity.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: lixing
 * @company: Persagy Technology Co.,Ltd
 * @since: 2020/9/9 2:10 下午
 * @version: V1.0
 **/
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(AuditableEntity.DO_PROP_CREATIONTIME)) {
            this.setFieldValByName(AuditableEntity.DO_PROP_CREATIONTIME, new Date(), metaObject);
        }
        if (metaObject.hasSetter(AuditableEntity.DO_PROP_MODIFIEDTIME)) {
            this.setFieldValByName(AuditableEntity.DO_PROP_MODIFIEDTIME, new Date(), metaObject);
        }
        if (metaObject.hasSetter(BaseEntity.PROP_VALID)) {
            this.setFieldValByName(BaseEntity.PROP_VALID, ValidEnum.TRUE.getType(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(AuditableEntity.DO_PROP_MODIFIEDTIME)) {
            this.setFieldValByName(AuditableEntity.DO_PROP_MODIFIEDTIME, new Date(), metaObject);
        }
    }
}
