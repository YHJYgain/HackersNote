package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录请求体封装类.
 * <p>
 * 用于封装用户登录的请求体，包含用户名和密码字段.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    /**
     * 用户名，用于用户身份验证.
     */
    private String username;
    /**
     * 密码，用于用户身份验证.
     */
    private String password;
}
