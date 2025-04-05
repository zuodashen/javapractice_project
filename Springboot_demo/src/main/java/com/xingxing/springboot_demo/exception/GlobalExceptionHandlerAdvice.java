package com.xingxing.springboot_demo.exception;

import com.xingxing.springboot_demo.pojo.ResponseMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   //统一处理
public class GlobalExceptionHandlerAdvice {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
    @ExceptionHandler({Exception.class}) //什么异常的统一处理
    public ResponseMessage handelerException(Exception e, HttpServletResponse request,HttpServletResponse response){
        //记录日志
        log.error("统一异常",e);
        return new ResponseMessage(500,"error",null);
    }

}













