package com.xiao.config;

import com.xiao.entity.User;

public class ThreadLocalUser {
    private static final ThreadLocal<User> me = new ThreadLocal<>();
    public void set(User user) {
        me.set(user);
    }
    public User get() {
        return me.get();
    }
}
