package com.persagy.dc.message.constant;

import lombok.Getter;

/**
 * 消息发送渠道
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Getter
public enum MessageChannelEnum {

    /** 短信 */
    SMS("SMS", "minioStorageServiceImpl"),
    /** APP */
    APP("APP", "minioStorageServiceImpl"),
    /** 邮箱 */
    MAIL("MAIL", "minioStorageServiceImpl"),
    /** 微信 */
    WECHAT("WECHAT", "minioStorageServiceImpl"),
    /** 新云 */
    XY("XY", "minioStorageServiceImpl");

    private String index;
    private String name;

    MessageChannelEnum(String index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * 获取Enum
     * @param index
     * @return
     */
    public static MessageChannelEnum load(String index) {
        for (MessageChannelEnum value: values()) {
            if (value.getIndex().equals(index)) {
                return value;
            }
        }
        return null;
    }
}
