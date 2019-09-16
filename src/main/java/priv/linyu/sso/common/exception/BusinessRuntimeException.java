package priv.linyu.sso.common.exception;

import priv.linyu.sso.common.enums.ResponseEnum;

/**
 * @className: BusinessRuntimeException
 * @author: QiuShangLin
 * @description:
 * @date: 2019/09/16 17:33
 * @version: 1.0
 **/
public class BusinessRuntimeException extends RuntimeException{

    private ResponseEnum responseEnum;

    public BusinessRuntimeException() {
    }


    public BusinessRuntimeException(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    public BusinessRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }
}
