package com.example.hackersnote.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类，表示应用中的用户信息.
 * <p>
 * 每个用户拥有唯一的用户名、昵称、头像和密码等基本信息.
 * 用户与 {@link Article} 实体存在多对多的关系：用户可以发布、点赞、收藏博文.
 * 用户之间还可以互相关注。
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 用户的唯一标识 ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户的头像 URL.
     */
    @Column(nullable = false)
    private String avatar;

    /**
     * 用户的昵称.
     */
    @Column(nullable = false)
    private String nickname;

    /**
     * 用户的用户名，必须唯一.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * 用户的密码.
     */
    @Column(nullable = false)
    private String password;

    /**
     * 用户的注册时间.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    /**
     * 用户的信息更新时间.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    /**
     * 用户发布的博文集合，与 {@link Article} 实体的作者关联.
     */
    @JsonBackReference
    @OneToMany(mappedBy = "author")
    private Set<Article> authoredArticles = new HashSet<>();

    /**
     * 用户点赞的博文集合，与 {@link Article} 实体的 likedByUsers 关联.
     */
    @JsonBackReference
    @ManyToMany(mappedBy = "likedByUsers")
    private Set<Article> likedArticles = new HashSet<>();

    /**
     * 用户收藏的博文集合，与 {@link Article} 实体的 collectedByUsers 关联.
     */
    @JsonBackReference
    @ManyToMany(mappedBy = "collectedByUsers")
    private Set<Article> collectedArticles = new HashSet<>();

    /**
     * 用户关注的其他用户集合。
     * <p>
     * 使用多对多关系来表示用户之间的关注关系.
     * </p>
     */
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_follow",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> followedUsers = new HashSet<>();
}
