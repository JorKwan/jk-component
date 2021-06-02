package com.persagy.yc.common.exception;


import com.persagy.yc.common.constant.ResponseCode;

/**
 * @description 
 * @author zhangqiankun
 * @since 2020年8月26日:	下午5:17:08
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -3325873096967595156L;
	
	private String errorCode;
    private String errorDesc;		// 给前端看
    
	private Object respJson; 		// 响应content中的数据，可加可不加

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * @param errorDesc
     */
    public BusinessException(String errorDesc) {
    	super(errorDesc);
        this.errorCode = ResponseCode.B0001.getCode();
        this.errorDesc = errorDesc;
    }
    
    /**
     * 业务异常错误码和错误描述构造器
     *
     * @param errorCode
     * @param errorDesc
     */
    public BusinessException(String errorCode, String errorDesc) {
    	super(errorDesc);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
    
    public BusinessException(String errorCode, String errorDesc, Object respJson) {
    	super(errorDesc);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.respJson = respJson;
    }

    /**
     * 业务异常信息、错误码和错误描述构造器
     *
     * @param message 后台日志看
     * @param errorCode 错误码
     * @param errorDesc	给前端看
     */
    public BusinessException(String message, String errorCode, String errorDesc, Object respJson) {
        super(message);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.respJson = respJson;
    }

    public BusinessException(Throwable cause, String errorCode, String errorDesc) {
        super(cause);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public BusinessException(String message, Throwable cause, String errorCode, String errorDesc) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

	public Object getRespJson() {
		return respJson;
	}

	public void setRespJson(Object respJson) {
		this.respJson = respJson;
	}
    
}
