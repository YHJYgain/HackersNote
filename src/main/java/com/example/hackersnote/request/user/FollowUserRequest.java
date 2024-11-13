package com.example.hackersnote.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关注用户请求体封装类.
 * <p>
 * 用于封装用户关注操作的请求体，包含被关注用户的 ID.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserRequest {
    /**
     * 被关注用户的 ID，用于标识目标用户.
     */
    private Long followedUserId;
}
