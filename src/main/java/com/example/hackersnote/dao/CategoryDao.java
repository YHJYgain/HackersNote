package com.example.hackersnote.dao;

import com.example.hackersnote.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类数据接口访问类，用于执行与 {@link Category} 实体相关的数据库操作.
 */
@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
    /**
     * 判断是否存在指定名称的分类.
     *
     * @param name 分类名称
     * @return 如果存在具有该名称的分类，则返回 true；否则返回 false
     */
    boolean existsByName(String name);

    /**
     * 根据文章 ID 查询该文章所属的所有分类.
     *
     * @param articleId 文章 ID
     * @return 包含该文章所属分类的列表
     */
    List<Category> findByCategorizedArticlesId(Long articleId);
}
