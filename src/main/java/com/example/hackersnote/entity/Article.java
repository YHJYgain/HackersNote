package com.example.hackersnote.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 博文实体类，表示一篇博文的信息，包括标题、内容、作者等.
 * 该类与其他实体（如 {@link User}、{@link Category}）有关系映射.
 * <p>
 * 每篇博文包含标题、封面图、描述、正文、发布时间、更新时间等属性，
 * 并与 {@link User} 关联表示作者、点赞用户和收藏用户，
 * 与 {@link Category} 关联表示该博文所属的分类.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = 4328743;

    /**
     * 博文的唯一标识 ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博文的标题.
     */
    @Column(nullable = false)
    private String title;

    /**
     * 博文的封面图 URL.
     */
    @Column(name = "featured_image", nullable = false)
    private String featuredImage;

    /**
     * 博文的描述信息.
     */
    @Column(nullable = false)
    private String description;

    /**
     * 博文的正文内容，支持大文本格式.
     */
    @Lob
    @Column(nullable = false)
    private String content;

    /**
     * 博文的发布时间.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    /**
     * 博文的最后更新时间.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    /**
     * 博文的作者，关联 {@link User} 实体.
     */
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * 博文所属的分类集合，关联 {@link Category} 实体.
     */
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    /**
     * 点赞该博文的用户集合，关联 {@link User} 实体.
     */
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "article_liked",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedByUsers = new HashSet<>();

    /**
     * 收藏该博文的用户集合，关联 {@link User} 实体.
     */
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "article_collected",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> collectedByUsers = new HashSet<>();
}
