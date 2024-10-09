package com.example.hackersnote.request.user;

import lombok.*;

/**
 * 更新用户请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String avatar;
    private String nickname;

} // end class UpdateUserRequest
