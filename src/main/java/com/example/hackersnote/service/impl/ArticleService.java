package com.example.hackersnote.service.impl;

import com.example.hackersnote.dao.*;
import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.article.*;
import com.example.hackersnote.request.category.*;
import com.example.hackersnote.request.user.*;
import com.example.hackersnote.result.*;
import com.example.hackersnote.service.IArticleService;
import jakarta.annotation.PostConstruct;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 博文服务类
 */
@Service
public class ArticleService implements IArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    private Subject currentSubject;
    private String currentUsername;
    private User currentUser;

    /**
     * 初始化博文服务所需资源
     */
    @PostConstruct
    public void init() {
        currentSubject = SecurityUtils.getSubject();
    } // end init()

    /**
     * 发布博文业务
     *
     * @param publishArticleRequest 发布博文请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> publishArticle(PublishArticleRequest publishArticleRequest) {
        if (currentSubject.isAuthenticated()) {
            Article newArticle = new Article();

            newArticle.setTitle(publishArticleRequest.getTitle());
            newArticle.setFeaturedImage(publishArticleRequest.getFeaturedImage());
            newArticle.setDescription(publishArticleRequest.getDescription());
            newArticle.setContent(publishArticleRequest.getContent());
            newArticle.setPublishDate(new Date());
            newArticle.setUpdateDate(new Date());

            currentUsername = (String) currentSubject.getPrincipal();
            currentUser = userDao.findUserByUsername(currentUsername);
            newArticle.setAuthor(currentUser);

            List<Category> categoriesList = (List<Category>) categoryDao.findAllById(publishArticleRequest.getCategoryIds());
            Set<Category> categories = new HashSet<>(categoriesList);
            newArticle.setCategories(categories);

            articleDao.save(newArticle);

            return Result.success(ResultCode.PUBLISH_ARTICLE_SUCCESS, newArticle);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end createArticle()

    /**
     * 更新博文信息业务
     *
     * @param updateArticleRequest 更新博文请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> updateArticle(UpdateArticleRequest updateArticleRequest) {
        Article updatedArticle = articleDao.findById(updateArticleRequest.getArticleId()).orElse(null);

        if (updatedArticle != null) {
            updatedArticle.setTitle(updateArticleRequest.getTitle());
            updatedArticle.setFeaturedImage(updateArticleRequest.getFeaturedImage());
            updatedArticle.setDescription(updateArticleRequest.getDescription());
            updatedArticle.setContent(updateArticleRequest.getContent());
            updatedArticle.setUpdateDate(new Date());

            updatedArticle.getCategories().clear();
            List<Category> categoriesList = (List<Category>) categoryDao.findAllById(updateArticleRequest.getCategoryIds());
            Set<Category> categories = new HashSet<>(categoriesList);
            updatedArticle.setCategories(categories);

            articleDao.save(updatedArticle);

            return Result.success(ResultCode.UPDATE_ARTICLE_SUCCESS, updatedArticle);
        } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
    } // end updateArticle()

    /**
     * 关键字查询博客业务
     *
     * @param searchRequest 关键词查询博文请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> searchArticles(SearchRequest searchRequest) {
        List<Article> articles = articleDao.findByTitleContaining(searchRequest.getQuery());

        if (!articles.isEmpty()) {
            return Result.success(ResultCode.SEARCH_ARTICLES_SUCCESS, articles);
        } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
    } // end searchArticles()

    /**
     * 统计已发布博文数量业务
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getArticlesCount() {
        return Result.success(articleDao.count());
    } // end getArticlesCount()

    /**
     * 查看博文信息业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> getArticleDetail(ArticleIdRequest articleIdRequest) {
        Article article = articleDao.findById(articleIdRequest.getArticleId()).orElse(null);

        if (article != null) {
            return Result.success(ResultCode.GET_ARTICLE_SUCCESS, article);
        } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
    } // end getArticle()

    /**
     * 查看指定分类下的博文业务
     *
     * @param categoryIdRequest 分类 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> getArticlesByCategoryId(CategoryIdRequest categoryIdRequest) {
        List<Article> articles = articleDao.findByCategoryId(categoryIdRequest.getCategoryId());

        if (articles != null) {
            return Result.success(ResultCode.SEARCH_ARTICLES_SUCCESS, articles);
        } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
    } // end getArticlesByCategoryId()

    /**
     * 分页查询博客业务
     *
     * @param pageable 分页查询博客请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Page<Article>> getArticlesByPage(Pageable pageable) {
        Page<Article> articles = articleDao.findAll(pageable);

        if (articles != null) {
            return Result.success(ResultCode.SEARCH_ARTICLES_SUCCESS, articles);
        } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
    } // end getArticlesByPage()

    /**
     * 查看指定用户发布的博文业务
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> getArticlesByUserId(UserIdRequest userIdRequest) {
        List<Article> articles = articleDao.findByAuthorId(userIdRequest.getUserId());
        return Result.success(articles);
    } // end getArticlesByUserId()

    /**
     * 博文点赞业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> likeArticle(ArticleIdRequest articleIdRequest) {
        if (currentSubject.isAuthenticated()) {
            currentUsername = (String) currentSubject.getPrincipal();
            currentUser = userDao.findUserByUsername(currentUsername);

            Article likedArticle = articleDao.findById(articleIdRequest.getArticleId()).orElse(null);

            if (likedArticle != null) {
                Set<User> likedUsers = articleDao.findLikedUsersById(articleIdRequest.getArticleId());

                if (!likedUsers.contains(currentUser)) {
                    likedUsers.add(currentUser);
                    likedArticle.getLikedByUsers().add(currentUser);
                    articleDao.save(likedArticle);

                    return Result.success(ResultCode.LIKED_SUCCESS, likedUsers);
                } else {
                    likedUsers.remove(currentUser);
                    likedArticle.getLikedByUsers().remove(currentUser);
                    articleDao.save(likedArticle);

                    return Result.success(ResultCode.UNLIKED_SUCCESS, likedUsers);
                }
            } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end likeArticle()

    /**
     * 博客收藏业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> collectArticle(ArticleIdRequest articleIdRequest) {
        if (currentSubject.isAuthenticated()) {
            currentUsername = (String) currentSubject.getPrincipal();
            currentUser = userDao.findUserByUsername(currentUsername);

            Article collectedArticle = articleDao.findById(articleIdRequest.getArticleId()).orElse(null);

            if (collectedArticle != null) {
                Set<User> collectedUsers = articleDao.findCollectedUsersById(articleIdRequest.getArticleId());

                if (!collectedUsers.contains(currentUser)) {
                    collectedUsers.add(currentUser);
                    collectedArticle.getCollectedByUsers().add(currentUser);
                    articleDao.save(collectedArticle);

                    return Result.success(ResultCode.COLLECTED_SUCCESS, collectedUsers);
                } else {
                    collectedUsers.remove(currentUser);
                    collectedArticle.getCollectedByUsers().remove(currentUser);
                    articleDao.save(collectedArticle);

                    return Result.success(ResultCode.UNCOLLECTED_SUCCESS, collectedUsers);
                }
            } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end collectArticle()

    /**
     * 统计所有博文的点赞量之和业务
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getTotalLikesCount() {
        return Result.success(articleDao.getTotalLikesCount());
    } // end getTotalLikesCount()

    /**
     * 查询特定博文点赞的用户业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> getLikedUsersById(ArticleIdRequest articleIdRequest) {
        return Result.success(ResultCode.GET_LIKED_USERS_SUCCESS,
                articleDao.findLikedUsersById(articleIdRequest.getArticleId()));
    } // end getLikedUsersById()

    /**
     * 统计所有博文收藏量之和业务
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getTotalCollectsCount() {
        return Result.success(articleDao.getTotalCollectsCount());
    } // end getTotalCollectsCount()

    /**
     * 查询特定博文收藏的用户业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> getCollectedUsersById(ArticleIdRequest articleIdRequest) {
        return Result.success(ResultCode.GET_COLLECTED_USERS_SUCCESS,
                articleDao.findCollectedUsersById(articleIdRequest.getArticleId()));
    } // end getCollectedUsersById()

    /**
     * 获取特定用户收藏的博文业务
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> getCollectedArticlesByUserId(UserIdRequest userIdRequest) {
        return Result.success(articleDao.findCollectedArticlesByUserId(userIdRequest.getUserId()));
    } // end getCollectedArticlesByUserId()

    /**
     * 删除博文业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> deleteArticle(ArticleIdRequest articleIdRequest) {
        if (currentSubject.isAuthenticated()) {
            Article deletedArticle = articleDao.findById(articleIdRequest.getArticleId()).orElse(null);

            if (deletedArticle != null) {
                articleDao.delete(deletedArticle);

                return Result.success(ResultCode.DELETE_ARTICLE_SUCCESS, deletedArticle);
            } else return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end deleteArticle()
} // end class ArticleService
