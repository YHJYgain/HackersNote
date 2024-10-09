package com.example.hackersnote.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * 博文实体类
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = 4328743;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 博文 ID

    @Column(nullable = false)
    private String title; // 标题

    @Column(name = "featured_image", nullable = false)
    private String featuredImage; // 封面

    @Column(nullable = false)
    private String description; // 描述

    @Lob
    @Column(nullable = false)
    private String content; // 正文

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date", nullable = false)
    private Date publishDate; // 发布时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate; // 更新时间

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author; // 作者

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>(); // 关联的分类集合

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "article_liked",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedByUsers = new HashSet<>(); // 点赞关系

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "article_collected",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> collectedByUsers = new HashSet<>(); // 收藏关系
} // end class Article
