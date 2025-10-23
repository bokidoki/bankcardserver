package com.keepsafe.bankcardserver.utils;

import com.keepsafe.bankcardserver.data.dto.BaseResp;
import com.keepsafe.bankcardserver.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public BaseResp<String> runtimeExceptionHandle(RuntimeException e) {
        logger.error(e.getMessage(), e);
        BaseResp<String> resp = new BaseResp<>();
        resp.setData(e.getMessage());
        resp.setCode(500);
        return resp;
    }

    @ExceptionHandler
    @ResponseBody
    public BaseResp<String> bizExceptionHandle(BizException e) {
        logger.error(e.getMessage(), e);
        BaseResp<String> resp = new BaseResp<>();
        resp.setData(e.getMessage());
        resp.setCode(e.getCode());
        return resp;
    }
}
