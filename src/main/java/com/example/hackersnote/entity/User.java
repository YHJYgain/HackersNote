package com.example.hackersnote.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

/**
 * 用户实体类
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 用户 ID

    @Column(nullable = false)
    private String avatar; // 头像

    @Column(nullable = false)
    private String nickname; // 昵称

    @Column(nullable = false, unique = true)
    private String username; // 用户名

    @Column(nullable = false)
    private String password; // 密码

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate; // 注册时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate; // 更新时间

    @JsonBackReference
    @OneToMany(mappedBy = "author")
    private Set<Article> authoredArticles  = new HashSet<>(); // 发布的博文集合

    @JsonBackReference
    @ManyToMany(mappedBy = "likedByUsers")
    private Set<Article> likedArticles = new HashSet<>(); // 用户点赞的博文集合

    @JsonBackReference
    @ManyToMany(mappedBy = "collectedByUsers")
    private Set<Article> collectedArticles = new HashSet<>(); // 用户收藏的博文集合

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_follow",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> followedUsers = new HashSet<>(); // 关注的用户集合
} // end class User
