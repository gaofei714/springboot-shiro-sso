package priv.linyu.sso.common.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import priv.linyu.sso.common.enums.ResponseEnum;
import priv.linyu.sso.common.exception.BusinessRuntimeException;
import priv.linyu.sso.common.util.ResponseEnvelope;

/**
 * @className: GlobalExceptionHandler
 * @author: QiuShangLin
 * @description: 全局异常处理器
 * @date: 2019/09/16 17:23
 * @version: 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    public ResponseEnvelope<String> businessHandlerException(BusinessRuntimeException e){
        log.error(e.getMessage(),e);
        return new ResponseEnvelope<>(e.getResponseEnum());
    }

    /**
     * 处理shiro 权限拦截异常
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEnvelope<String> authorizationException(AuthorizationException e){
        log.error(e.getMessage(),e);
        return new ResponseEnvelope<> (ResponseEnum.AUTH_ERROR);
    }


    /**
     * 自定义所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEnvelope<String> globalHandlerException(Exception e){
        log.error(e.getMessage(),e);
        return new ResponseEnvelope<>(ResponseEnum.ERROR);
    }


}
