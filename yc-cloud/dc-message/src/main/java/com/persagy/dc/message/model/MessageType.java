package com.persagy.dc.message.model;

import com.persagy.yc.common.model.entity.BaseEntity;
import lombok.Data;

/**
 * 消息类型
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Data
public class MessageType extends BaseEntity {

    /** 消息类型编码 */
    private String typeCode;
    /** 消息类型名称 */
    private String typeName;
    /** 消息发送渠道 - MessageChannelEnum */
    private String sendChannel;
    /** 备注 */
    private String remark;
}
