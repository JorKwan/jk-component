package com.persagy.dc.message.model;

import com.persagy.yc.common.model.entity.AuditableEntity;
import lombok.Data;

/**
 * 消息
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Data
public class MessageVO extends AuditableEntity {

    /** 消息类型 */
    private String typeId;
    /** 接收用户 */
    private String userId;
    /** 消息标题 */
    private String title;
    /** 消息详情 */
    private String content;
    /** 关联业务主键 */
    private String businessId;
    /** 所属项目 */
    private String projectId;
    /** 消息发送渠道 - MessageChannelEnum */
    private String sendChannel;
    /** 已读标识 */
    private Boolean readFlag;
    /** 置顶标识 */
    private Boolean topFlag;
}
