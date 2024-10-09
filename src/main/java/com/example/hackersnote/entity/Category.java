package com.example.hackersnote.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

/**
 * 分类实体类
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 分类 ID

    @Column(nullable = false, unique = true)
    private String name; // 分类名称

    @JsonBackReference
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Article> categorizedArticles = new HashSet<>(); // 分类关系
} // end class Category
