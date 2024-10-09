package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 用户 ID 请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdRequest {

    private Long userId;

} // end class GetUserInfoRequest
