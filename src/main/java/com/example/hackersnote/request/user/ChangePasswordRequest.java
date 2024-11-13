package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改密码请求体封装类.
 * <p>
 * 用于封装用户修改密码请求体，包含旧密码和新密码字段.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    /**
     * 旧密码，用于验证用户身份.
     */
    private String oldPassword;
    /**
     * 新密码，用于设置用户的新密码.
     */
    private String newPassword;
}
