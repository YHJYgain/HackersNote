package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证密码请求体封装类.
 * <p>
 * 用于封装密码验证的请求体。通常用于用户在操作时提供密码进行身份验证.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePasswordRequest {
    /**
     * 用户提供的密码，用于验证用户身份.
     */
    private String password;
}
