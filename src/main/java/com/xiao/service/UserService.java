package com.xiao.service;

import com.xiao.common.R;
import com.xiao.dto.UserDto;
import com.xiao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2025-02-06 16:23:28
*/
public interface UserService extends IService<User> {
    R<UserDto> login(User user);
}
