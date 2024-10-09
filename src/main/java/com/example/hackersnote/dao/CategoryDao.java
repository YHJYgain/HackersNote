package com.example.hackersnote.dao;

import com.example.hackersnote.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类数据接口访问类
 */
@Repository
public interface CategoryDao extends CrudRepository<Category, Long>  {

    // CrudRepository 内置了一些的 CRUD 方法：
    // Iterable<Category> CategoryDao.findAll();
    // Iterable<Category> CategoryDao.findById(id);
    // boolean CategoryDao.existsById(id);
    // Category CategoryDao.save(category);
    // Iterable<Category> CategoryDao.saveAll(Iterable<Category>);
    // void CategoryDao.deleteById(id);
    // void CategoryDao.delete(category);
    // void CategoryDao.deleteAll();

    boolean existsByName(String name);
    Category findCategoryByName(String name);
    List<Category> findByCategorizedArticlesId(Long articleId);

} // end interface CategoryDao
