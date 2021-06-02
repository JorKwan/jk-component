package com.persagy.yc.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.persagy.yc.common.constant.ResponseCode;
import lombok.Data;

/**
 * 服务调用消息结果
 * @author Charlie Yu
 * @date 2021-03-25
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {

    /** 不需要提示 */
    public static final CommonResult SUCCESS = new CommonResult();

    private String respCode;
    private String respMsg;
    private T content;

    /**
     * 构造方法
     */
    public CommonResult(){
        this(ResponseCode.A00000.getCode(), ResponseCode.A00000.getDesc());
    }

    /**
     * 构造方法
     * @param respCode 响应码
     * @param respMsg 提示信息
     */
    public CommonResult(String respCode, String respMsg) {
        this(respCode, respMsg,null);
    }

    /**
     * 构造方法
     * @param respCode 响应码
     * @param respMsg 提示信息
     * @param content 数据
     */
    public CommonResult(String respCode, String respMsg, T content){
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.content = content;
    }

    public static <T> CommonResult<T> success(T t) {
        return  new CommonResult<>(ResponseCode.A00000.getCode(), ResponseCode.A00000.getDesc(),t);
    }
}
