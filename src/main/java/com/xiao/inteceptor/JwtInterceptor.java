package com.xiao.inteceptor;

import com.xiao.constants.MyConstants;
import com.xiao.exception.LoginException;
import com.xiao.utils.JjwtUtil;
import com.xiao.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            log.info("用户未登录");
            response.setStatus(401);
            return false;
        }
        Claims claims = JjwtUtil.parseJWT(token);
        String username = (String) claims.get("username");
        String key = MyConstants.LOGIN_PREFIX + username;
        Object res = redisUtil.get(key);
        if (res == null) {
            log.info("用户 {} 已过期", username);
            throw new LoginException();
        }
        log.info("用户 {} 已登录! 剩余时间 {} 秒", username, redisUtil.getExp(key));
        redisUtil.expire(key, 30, TimeUnit.MINUTES);
        return true;
    }
}
