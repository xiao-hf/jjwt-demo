package com.xiao.controller;

import com.xiao.common.R;
import com.xiao.dto.UserDto;
import com.xiao.entity.User;
import com.xiao.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("test")
    public R<String> test() {
        return R.success(null);
    }

    @PostMapping("login")
    public R<UserDto> login(@RequestBody User user) {
        return userService.login(user);
    }
}
