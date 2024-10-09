package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 用户登录请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

} // end class LoginRequest
