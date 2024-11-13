package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册请求体封装类.
 * <p>
 * 用于封装用户注册的请求体，包含用户名、密码、昵称和头像等信息.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    /**
     * 用户名，用于用户的唯一标识.
     */
    private String username;
    /**
     * 密码，用于用户注册时设置账户密码.
     */
    private String password;
    /**
     * 昵称，用于用户显示名称.
     */
    private String nickname;
    /**
     * 头像，用于用户展示的个人图像.
     */
    private String avatar;
}
