package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 ID 请求体封装类.
 * <p>
 * 用于封装用户 ID 的请求体，通常用于根据用户 ID 进行相关操作.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdRequest {
    /**
     * 用户 ID，用于唯一标识用户.
     */
    private Long userId;
}
