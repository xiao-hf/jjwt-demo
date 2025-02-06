package com.xiao.dto;

import com.xiao.entity.User;
import lombok.Data;

@Data
public class UserDto extends User {
    private String token;
}
