package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 用户注册请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String nickname;
    private String avatar;

} // end class LoginRequest
