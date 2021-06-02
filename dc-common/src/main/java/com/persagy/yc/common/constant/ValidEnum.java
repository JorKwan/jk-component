package com.persagy.yc.common.constant;

/**
 * @description: 合法标志
 * @author: lixing
 * @company: Persagy Technology Co.,Ltd
 * @since: 2021/3/9 2:56 下午
 * @version: V1.0
 */
public enum ValidEnum {
    FALSE(0, false),
    TRUE(1, true);

    private Integer type;
    private Boolean desc;

    ValidEnum(Integer type, Boolean desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }
}
