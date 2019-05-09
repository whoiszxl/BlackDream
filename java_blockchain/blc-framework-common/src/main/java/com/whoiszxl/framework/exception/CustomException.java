package com.whoiszxl.framework.exception;

import com.whoiszxl.framework.model.response.ResultCode;

/**
 * 自定义异常类型
 * @author Administrator
 *
 */
public class CustomException extends RuntimeException{

    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
    
    public ResultCode getResultCode() {
        return resultCode;
    }
    
}
