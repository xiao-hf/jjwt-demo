package com.xiao;

import com.xiao.entity.User;
import com.xiao.utils.JjwtUtil;
import com.xiao.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

@SpringBootTest
class JjwtDemoApplicationTests {

    @Resource
    RedisUtil redisUtil;

    @Test
    void testRedis() {
        Object o = redisUtil.get("23434324");
        System.out.println(o);
    }

    @Test
    void test() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("xiaobai");
        user.setPassword("123456");
        String jwt = JjwtUtil.createJWT("dalao", "Tom", user, "xiaobai1");
        Claims claims = JjwtUtil.parseJWT(jwt);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println(claims);
    }

}
