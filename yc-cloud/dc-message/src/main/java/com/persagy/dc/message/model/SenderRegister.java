package com.persagy.dc.message.model;

import com.persagy.yc.common.model.entity.BaseEntity;
import lombok.Data;

/**
 * 发送渠道注册信息
 * @author Charlie Yu
 * @date 2021-05-26
 */
@Data
public class SenderRegister extends BaseEntity {

    /** 发送渠道编码 */
    private String code;
    /** 发送渠道名称 */
    private String name;
    /** accessKey */
    private String accessKey;
    /** accessSecret */
    private String accessSecret;
    /** 扩展参数 */
    private String extra;
    /** 发送消息实现类 IMessageSender */
    private String clazz;

}
