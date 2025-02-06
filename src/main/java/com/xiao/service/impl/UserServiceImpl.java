package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.common.R;
import com.xiao.constants.MyConstants;
import com.xiao.dto.UserDto;
import com.xiao.entity.User;
import com.xiao.service.UserService;
import com.xiao.mapper.UserMapper;
import com.xiao.utils.JjwtUtil;
import com.xiao.utils.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-02-06 16:23:28
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    RedisUtil redisUtil;

    @Resource
    UserMapper userMapper;

    @Override
    public R<UserDto> login(User user) {
        String username = user.getUsername();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        User record = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (record == null || !Objects.equals(user.getPassword(), record.getPassword())) {
            log.info("用户名或密码错误");
            return R.fail("用户名或密码错误, 请重新登录!", null);
        }
        userDto.setToken(JjwtUtil.createJWT(null, null, user, null));
        redisUtil.set(MyConstants.LOGIN_PREFIX + user.getUsername(), "1", 30, TimeUnit.MINUTES);
        return R.success(userDto);
    }
}




