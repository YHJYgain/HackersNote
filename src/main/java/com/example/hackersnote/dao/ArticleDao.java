package com.example.hackersnote.dao;

import com.example.hackersnote.entity.Article;
import com.example.hackersnote.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 博文数据接口访问类，用于执行与 {@link Article} 实体相关的数据库操作.
 */
@Repository
public interface ArticleDao extends CrudRepository<Article, Long> {
    /**
     * 分页查询所有博文.
     *
     * @param pageable 分页参数
     * @return 包含分页博文的 {@link Page} 对象
     */
    Page<Article> findAll(Pageable pageable);

    /**
     * 根据标题中的关键字查询博文.
     *
     * @param keyword 标题中的关键字
     * @return 包含匹配博文的列表
     */
    List<Article> findByTitleContaining(String keyword);

    /**
     * 根据分类 ID 查询属于该分类的所有博文.
     *
     * @param categoryId 分类 ID
     * @return 包含匹配博文的列表
     */
    @Query("SELECT a FROM Article a JOIN a.categories c WHERE c.id = :categoryId")
    List<Article> findByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 根据作者 ID 查询其发布的所有博文.
     *
     * @param userId 作者的用户 ID
     * @return 包含作者发布的博文列表
     */
    List<Article> findByAuthorId(Long userId);

    /**
     * 获取所有博文的总点赞数.
     *
     * @return 所有博文的总点赞数
     */
    @Query("SELECT SUM(size(a.likedByUsers)) FROM Article a")
    long getTotalLikesCount();

    /**
     * 根据博文 ID 查询点赞该博文的所有用户.
     *
     * @param articleId 博文 ID
     * @return 点赞该博文的用户集合
     */
    @Query("SELECT a.likedByUsers FROM Article a WHERE a.id = :articleId")
    Set<User> findLikedUsersById(@Param("articleId") Long articleId);

    /**
     * 获取所有博文的总收藏数.
     *
     * @return 所有博文的总收藏数
     */
    @Query("SELECT SUM(size(a.collectedByUsers)) FROM Article a")
    long getTotalCollectsCount();

    /**
     * 根据博文 ID 查询收藏该博文的所有用户.
     *
     * @param articleId 博文 ID
     * @return 收藏该博文的用户集合
     */
    @Query("SELECT a.collectedByUsers FROM Article a WHERE a.id = :articleId")
    Set<User> findCollectedUsersById(@Param("articleId") Long articleId);

    /**
     * 根据用户 ID 查询其收藏的所有博文.
     *
     * @param userId 用户 ID
     * @return 用户收藏的博文列表
     */
    @Query("SELECT a FROM Article a JOIN a.collectedByUsers u WHERE u.id = :userId")
    List<Article> findCollectedArticlesByUserId(@Param("userId") Long userId);
}
