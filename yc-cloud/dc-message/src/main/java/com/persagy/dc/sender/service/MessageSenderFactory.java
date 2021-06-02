package com.persagy.dc.sender.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.persagy.dc.message.model.MessageVO;
import com.persagy.dc.message.model.SenderRegister;
import com.persagy.dc.message.service.ISenderRegisterService;
import com.persagy.yc.common.helper.SpringHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息发送 工厂
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Slf4j
public class MessageSenderFactory {

    /** 消息处理器 */
    private static Map<String, IMessageSender> senderMap = new HashMap<>(16);

    /**
     * 发送消息
     * @param vo
     */
    public void sendMessage(MessageVO vo) {
        if(vo == null || StrUtil.isBlank(vo.getTypeId())
                || StrUtil.isBlank(vo.getSendChannel())) {
            return;
        }
        String[] channels = StrUtil.split(vo.getSendChannel(), StrUtil.COMMA);
        // 发送
        for(String channel:channels) {
            IMessageSender sender = senderMap.get(channel);
            if(sender == null) {
                log.error("没有找到【{}】对应的消息配置！", channel);
                continue;
            }
            sender.send(vo);
        }
    }

    /**
     * 懒加载
     * @return
     */
    private Map<String, IMessageSender> getSenderList() {
        if(MapUtil.isEmpty(senderMap)) {
            ISenderRegisterService senderService = SpringHelper.getBean(ISenderRegisterService.class);
            List<SenderRegister> registers = senderService.queryAll();
            initSender(registers);
        }
        return senderMap;
    }

    /**
     * 重置senderMap
     * @param registers
     */
    private void initSender(List<SenderRegister> registers) {
        // 先清空Map
        senderMap.clear();
        if(CollUtil.isEmpty(registers)) {
            return;
        }
        for(SenderRegister register:registers) {
            // 配置检查
            if(StrUtil.isBlank(register.getCode()) || StrUtil.isBlank(register.getClazz())) {
                continue;
            }
            // 得到消息处理器
            IMessageSender sender = SpringHelper.getBean(register.getClazz(), IMessageSender.class);
            // 调用消息处理器初始化
            boolean success = sender.initParam(register);
            if(!success) {
                continue;
            }
            senderMap.put(register.getCode(), sender);
        }
    }
}
