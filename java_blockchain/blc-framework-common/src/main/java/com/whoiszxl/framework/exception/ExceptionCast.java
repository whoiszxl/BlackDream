package com.whoiszxl.framework.exception;

import com.whoiszxl.framework.model.response.ResultCode;

public class ExceptionCast {
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
