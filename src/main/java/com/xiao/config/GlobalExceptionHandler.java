package com.xiao.config;

import com.xiao.common.R;
import com.xiao.exception.LoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public R<String> LoginExceptionHandler() {
        return R.fail("用户已过期, 请重新登录!", null);
    }
}
