package com.persagy.yc.common.utils;


import cn.hutool.core.collection.CollUtil;
import com.persagy.yc.common.constant.ResponseCode;
import com.persagy.yc.common.exception.BusinessException;
import com.persagy.yc.common.model.response.CommonResult;
import com.persagy.yc.common.model.response.PageList;

import java.util.List;

/**
 * 响应结果包装助手
 * @author Charlie Yu
 * @date 2021-03-25
 */
public class ResultHelper {

    /**
     * 成功消息
     * @return
     */
    public static CommonResult success(){
        return new CommonResult(ResponseCode.A00000.getCode(), ResponseCode.A00000.getDesc());
    }

    /**
     * 成功消息
     * @return
     */
    public static CommonResult success(String respMsg){
        return new CommonResult(ResponseCode.A00000.getCode(), respMsg);
    }

    /**
     * 失败消息
     * @param respCode
     * @param respMsg
     * @return
     */
    public static CommonResult failure(String respCode, String respMsg) {
        return new CommonResult(respCode, respMsg);
    }

    /**
     * 单个数据
     * @param content
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> single(T content) {
        return new CommonResult(ResponseCode.A00000.getCode(), ResponseCode.A00000.getDesc(), content);
    }

    /**
     * 单个数据
     * @param content
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> single(T content, String desc) {
        return new CommonResult(ResponseCode.A00000.getCode(), desc, content);
    }

    /**
     * 多个数据 - 列表
     * @param records
     * @param <T>
     * @return
     */
    public static <T> CommonResult<PageList<T>> multi(List<T> records) {
        return multi(records, CollUtil.size(records));
    }

    /**
     * 多个数据 - 分页
     * @param page
     * @param <T>
     * @return
     */
//    public static <T> CommonResult<PageList<T>> multi(IPage<T> page) {
//        List<T> records = page == null ? null : page.getRecords();
//        long total = page == null ? 0 : page.getTotal();
//        return multi(records, total);
//    }

    /**
     * 多个数据
     * @param records
     * @param total
     * @param <T>
     * @return
     */
    private static <T> CommonResult<PageList<T>> multi(List<T> records, long total) {
        PageList<T> pageList = new PageList<>(records, total);
        return new CommonResult(ResponseCode.A00000.getCode(), ResponseCode.A00000.getDesc(), pageList);
    }

    /**
     * 读取结果
     * @param result
     * @param <T>
     * @return 对应的结果
     */
    public static <T> T getContent(CommonResult<T> result) {
        if(!ResponseCode.A00000.getCode().equals(result.getRespCode())) {
            throw new BusinessException(result.getRespCode(), result.getRespMsg());
        }
        return result.getContent();
    }
}
