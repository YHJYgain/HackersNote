package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 修改密码请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;

} // end class ChangePasswordRequest
