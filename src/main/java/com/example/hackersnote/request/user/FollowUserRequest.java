package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 关注用户请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserRequest {

    private Long followedUserId;

} // end class FollowUserRequest
