package com.example.hackersnote.dao;

import com.example.hackersnote.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 博文数据接口访问类
 */
@Repository
public interface ArticleDao extends CrudRepository<Article, Long> {
    // CrudRepository 内置了一些的 CRUD 方法：
    // Iterable<Article> ArticleDao.findAll();
    // Iterable<Article> ArticleDao.findById(id);
    // boolean ArticleDao.existsById(id);
    // Article ArticleDao.save(article);
    // Iterable<Article> ArticleDao.saveAll(Iterable<Article>);
    // void ArticleDao.deleteById(id);
    // void ArticleDao.delete(article);
    // void ArticleDao.deleteAll();

    Page<Article> findAll(Pageable pageable);

    List<Article> findByTitleContaining(String keyword);

    @Query("SELECT a FROM Article a JOIN a.categories c WHERE c.id = :categoryId")
    List<Article> findByCategoryId(@Param("categoryId") Long categoryId);

    List<Article> findByAuthorId(Long userId);

    @Query("SELECT SUM(size(a.likedByUsers)) FROM Article a")
    long getTotalLikesCount();

    @Query("SELECT a.likedByUsers FROM Article a WHERE a.id = :articleId")
    Set<User> findLikedUsersById(@Param("articleId") Long articleId);

    @Query("SELECT SUM(size(a.collectedByUsers)) FROM Article a")
    long getTotalCollectsCount();

    @Query("SELECT a.collectedByUsers FROM Article a WHERE a.id = :articleId")
    Set<User> findCollectedUsersById(@Param("articleId") Long articleId);

    @Query("SELECT a FROM Article a JOIN a.collectedByUsers u WHERE u.id = :userId")
    List<Article> findCollectedArticlesByUserId(@Param("userId") Long userId);
} // end interface ArticleDao
