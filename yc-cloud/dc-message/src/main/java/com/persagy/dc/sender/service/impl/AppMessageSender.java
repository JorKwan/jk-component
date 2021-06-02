package com.persagy.dc.sender.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import com.persagy.dc.message.model.MessageVO;
import com.persagy.dc.message.model.SenderRegister;
import com.persagy.dc.sender.service.IMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * App消息
 * @author Charlie Yu
 * @date 2021-05-24
 */
@Component
@Slf4j
public class AppMessageSender implements IMessageSender {

    private IAcsClient client;

    @Override
    public boolean initParam(SenderRegister register) {
        // 配置校验
        if(StrUtil.isBlank(register.getAccessKey()) || StrUtil.isBlank(register.getAccessSecret())) {
            log.error("【{}】消息参数配置有误！", register.getCode());
            return false;
        }
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                register.getAccessKey(), register.getAccessSecret());
        client = new DefaultAcsClient(profile);
        return true;
    }

    @Override
    public void send(MessageVO vo) {
        PushRequest request = buildRequest(vo);
        String messageId = doSend(request);
    }

    /**
     * 执行发送
     * 5次重试
     * @param request
     * @return 返回的消息Id，为空表示执行失败
     */
    private String doSend(PushRequest request) {
        int count = 1;
        // 最多重试5次
        while (count <= 5) {
            try {
                PushResponse response = client.getAcsResponse(request);
                return response.getMessageId();
            } catch (ClientException e) {
                log.error("{}:{},重试第{}次", request.getTargetValue(), e.getMessage(), count);
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                }
                continue;
            }
        }
        return null;
    }

    /**
     * 构建推送参数
     * @param vo
     * @return
     */
    private PushRequest buildRequest(MessageVO vo) {
        PushRequest request = new PushRequest();
        // TODO 增加参数配置，appKey
        request.setAppKey(12312123123L);
        //推送目标: DEVICE:推送给设备; ACCOUNT:推送给指定帐号,TAG:推送给自定义标签; ALIAS: 按别名推送; ALL: 全推
        request.setTarget("ALIAS");
        //根据Target来设定，如Target=DEVICE, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        request.setTargetValue(vo.getUserId());
        // 设备类型deviceType, iOS设备: "iOS"; Android设备: "ANDROID"; 全部: "ALL", 这是默认值.
        request.setDeviceType("ALL");
        request.setBody(vo.getContent());
        // MESSAGE:表示消息(默认), NOTICE:表示通知
        request.setPushType("NOTICE");
        // 消息的标题
        request.setTitle(vo.getTitle());

        /** iOS参数配置 */
        // iOS应用图标右上角角标
        request.setIOSBadge(0);
        // iOS通知声音
        request.setIOSMusic("default");
        // iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。'DEV': 表示开发环境 'PRODUCT': 表示生产环境
        request.setIOSApnsEnv("PRODUCT");
        // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：**离线消息转通知仅适用于`生产环境`**
        request.setIOSRemind(true);
        // iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=`PRODUCT` && iOSRemind为true时有效
        request.setIOSRemindBody("PushRequest summary");
        // 通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
///        request.setIOSExtParameters("{\"k1\":\"ios\",\"k2\":\"v2\"}");

        /** 安卓参数配置 */
        request.setAndroidNotifyType("BOTH");
        request.setAndroidNotificationChannel("1");
        // 点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
        request.setAndroidOpenType("APPLICATION");
        // 设定通知打开的activity，仅当AndroidOpenType="Activity"有效
        request.setAndroidActivity("com.example.alipushdemo");
        // Android通知音乐
        request.setAndroidMusic("default");
        // 设置该参数后启动辅助弹窗功能, 此处指定通知点击后跳转的Activity（辅助弹窗的前提条件：1. 集成第三方辅助通道；2. StoreOffline参数设为true）
        request.setAndroidPopupActivity("APPLICATION");
        request.setAndroidPopupTitle(vo.getTitle());
        request.setAndroidPopupBody(vo.getContent());
        // 设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)
///        request.setAndroidExtParameters("{\"k1\":\"android\",\"k2\":\"v2\"}");

        /** 推送配置 */
        // 推送时间 - 不设置表示立即推送
        Date pushDate = new Date(System.currentTimeMillis());
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
        request.setPushTime(pushTime);
        // 失效时间 - 12小时后消息失效, 不会再发送
        Date expireDate = new Date(System.currentTimeMillis() + 12 * 3600 * 1000);
        String expireTime = ParameterHelper.getISO8601Time(expireDate);
        request.setExpireTime(expireTime);
        // 离线消息是否保存 - 若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        request.setStoreOffline(true);
        return request;
    }
}
