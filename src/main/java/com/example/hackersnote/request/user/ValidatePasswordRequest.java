package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 验证密码请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePasswordRequest {

    private String password;

} // end class ValidatePasswordRequest
