package com.example.hackersnote.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 分类实体类，表示博文所属的分类信息.
 * <p>
 * 每个分类包含一个唯一的名称和一个分类 ID。
 * 该类与 {@link Article} 实体存在多对多的关系，
 * 即每篇博文可以属于多个分类，每个分类也可以包含多篇博文.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    /**
     * 分类的唯一标识 ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名称，必须唯一.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * 属于该分类的博文集合，关联 {@link Article} 实体.
     */
    @JsonBackReference
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Article> categorizedArticles = new HashSet<>();
}
