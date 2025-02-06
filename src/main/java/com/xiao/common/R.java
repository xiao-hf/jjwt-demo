package com.xiao.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;
    public static <T> R<T> success(T data) {
        return new R<T>(1, "成功!", data);
    }
    public static <T> R<T> fail(String msg, T data) {
        return new R<T>(0, msg, data);
    }
}
