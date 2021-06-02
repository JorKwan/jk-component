package com.persagy.yc.common.constant;

/**
 * @description 
 * @author zhangqiankun
 * @since 2020年8月26日:	下午5:17:08
 */
public enum ResponseCode {

	A00000("00000", "success"),
	
	A10000("10000", "failure"),
    
	A0001("A0001", "用户端错误"),
	
	A0100("A0100", "用户注册错误"),
   
    A0135("A0135", "Method Not Allowed"),
    
    A0136("A0136", "Unsupported Media Type"),
    
    A0137("A0137", "Not Acceptable"),
	
	A0151("A0151", "手机格式校验失败"),

	A0152("A0152", "地址格式校验失败"),
    
	A0153("A0153", "邮箱格式校验失败"),

    A0154("A0154", "账号名称重复"),

    A0155("A0155", "员工编号重复"),

    A0156("A0156", "手机号重复"),

    A0157("A0157", "邮箱重复"),
    
    A0201("A0201", "用户账户不存在"),
    
    A0202("A0202", "用户账户被冻结"),
    
    A0203("A0203", "用户账户已作废"),
    
    A0210("A0210", "用户名/密码错误"),
    
	A0220("A0220", "用户身份校验失败"),
	
	A0301("A0301", "访问未授权"),
	
	A0302("A0302", "正在授权中"),
	
	A0303("A0303", "拒绝授权申请"),
	
	A0311("A0311", "授权已过期"),
	
	A0324("A0324", "网关访问受限"),
	
	A0340("A0340", "签名异常"),
	
	A0400("A0400", "用户请求参数错误"),
	
	A0402("A0402", "无效的用户输入"),
	
	A0422("A0422", "地址不在服务范围"),
	
	A0427("A0427", "请求JSON 解析失败"),
	
    B0001("B0001", "系统执行出错"),
    
    B0300("B0300", "系统资源异常"),
	
    C0001("C0001", "调用第三方服务出错"),
    
    C0100("C0100", "中间件服务出错"),
    
    C0110("C0110", "RPC服务出错"),
    
    C0134("C0134", "不支持的数据格式"),
    
    C0320("C0320", "数据不存在"),
    
    C0341("C0341", "主键冲突"),
	
	Z9999("Z9999", "系统繁忙");	// 未知异常
	
    private String code;
    
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private ResponseCode(String code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code + "_" + this.desc;
    }

}
