package com.example.hackersnote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 博主信息类，用于展示用户的基本信息和关联的博文.
 * <p>
 * 此类是用于展示的用户信息数据传输对象（DTO），包含用户的基本属性以及用户发布和收藏的博文集合.
 * 它从 {@link User} 实体类中提取信息，便于在前端展示用户相关信息.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    /**
     * 用户的唯一标识 ID.
     */
    private Long id;
    /**
     * 用户名，应用中的唯一标识符.
     */
    private String username;
    /**
     * 用户的昵称.
     */
    private String nickname;
    /**
     * 用户的头像 URL.
     */
    private String avatar;
    /**
     * 用户的创建时间，即注册时间.
     */
    private Date createDate;
    /**
     * 用户发布的博文集合.
     */
    private Set<Article> articles;
    /**
     * 用户收藏的博文集合.
     */
    private Set<Article> collectedArticles;

    /**
     * 从 {@link User} 实体构造 UserInfo 对象，提取用户的基本信息和相关博文集合.
     *
     * @param user {@link User} 实体对象
     */
    public UserInfo(final User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.createDate = user.getCreateDate();
        this.articles = user.getAuthoredArticles();
        this.collectedArticles = user.getCollectedArticles();
    }
}
