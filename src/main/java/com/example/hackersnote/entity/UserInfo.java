package com.example.hackersnote.entity;

import lombok.*;

import java.io.Serializable;
import java.util.*;

/**
 * 博主实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Long id; // 用户 ID
    private String username; // 用户名
    private String nickname; // 昵称
    private String avatar; // 头像
    private Date createDate; // 创建时间
    private Set<Article> articles; // 发布的博文集合
    private Set<Article> collectedArticles; // 用户收藏的博文集合

    public UserInfo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.createDate = user.getCreateDate();
        this.articles = user.getAuthoredArticles();
        this.collectedArticles = user.getCollectedArticles();
    } // end UserInfo(user)
} // end class UserInfo
