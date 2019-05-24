package com.jiefeng.ssm.web.exception;

import com.jiefeng.ssm.annotation.LoggerOrException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @LoggerOrException(operationName = "权限认证")
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Map<String,Object> permissionAuthc(){
        throw new UnauthorizedException("没有权限");
    }
}
