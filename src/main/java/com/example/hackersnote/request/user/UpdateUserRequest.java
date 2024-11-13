package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新用户请求体封装类.
 * <p>
 * 用于封装用户更新信息的请求体，包含用户头像和昵称字段.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    /**
     * 用户头像，用于更新用户的个人图像.
     */
    private String avatar;
    /**
     * 用户昵称，用于更新用户的显示名称.
     */
    private String nickname;
}
