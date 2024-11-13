package com.example.hackersnote.service.impl;

import com.example.hackersnote.dao.ArticleDao;
import com.example.hackersnote.dao.CategoryDao;
import com.example.hackersnote.dao.UserDao;
import com.example.hackersnote.entity.Article;
import com.example.hackersnote.entity.Category;
import com.example.hackersnote.entity.User;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.article.PublishArticleRequest;
import com.example.hackersnote.request.article.SearchRequest;
import com.example.hackersnote.request.article.UpdateArticleRequest;
import com.example.hackersnote.request.category.CategoryIdRequest;
import com.example.hackersnote.request.user.UserIdRequest;
import com.example.hackersnote.result.Result;
import com.example.hackersnote.result.ResultCode;
import com.example.hackersnote.service.IArticleService;
import jakarta.annotation.PostConstruct;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 博文服务类，提供了关于博文的管理和操作功能，包括发布、更新、查询、删除、点赞、收藏等业务操作.
 * 该类实现了 {@link IArticleService} 接口，处理所有与博文相关的业务逻辑.
 */
@Service
public class ArticleService implements IArticleService {
    /**
     * 博文数据访问对象，用于与数据库中的博文数据进行交互.
     */
    @Autowired
    private ArticleDao articleDao;
    /**
     * 用户数据访问对象，用于与数据库中的用户数据进行交互.
     */
    @Autowired
    private UserDao userDao;
    /**
     * 分类数据访问对象，用于与数据库中的分类数据进行交互.
     */
    @Autowired
    private CategoryDao categoryDao;
    /**
     * 当前用户的身份信息，用于获取当前认证用户的主体.
     */
    private Subject currentSubject;
    /**
     * 当前用户的用户名，用于标识当前的登录用户.
     */
    private String currentUsername;
    /**
     * 当前用户对象，用于获取当前登录用户的详细信息.
     */
    private User currentUser;

    /**
     * 初始化博文服务所需的资源，设置当前的安全主体.
     */
    @PostConstruct
    public void init() {
        currentSubject = SecurityUtils.getSubject();
    }

    /**
     * 发布博文业务.
     *
     * @param publishArticleRequest 发布博文请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> publishArticle(final PublishArticleRequest publishArticleRequest) {
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
        } else {
            return Result.fail(ResultCode.NOT_LOGGED_IN);
        }
    }

    /**
     * 更新博文信息业务.
     *
     * @param updateArticleRequest 更新博文请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> updateArticle(final UpdateArticleRequest updateArticleRequest) {
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
        } else {
            return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        }
    }

    /**
     * 关键字查询博客业务.
     *
     * @param searchRequest 关键词查询博文请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> searchArticles(final SearchRequest searchRequest) {
        List<Article> articles = articleDao.findByTitleContaining(searchRequest.getQuery());

        if (!articles.isEmpty()) {
            return Result.success(ResultCode.SEARCH_ARTICLES_SUCCESS, articles);
        } else {
            return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        }
    }

    /**
     * 统计已发布博文数量业务.
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getArticlesCount() {
        return Result.success(articleDao.count());
    }

    /**
     * 查看博文信息业务.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> getArticleDetail(final ArticleIdRequest articleIdRequest) {
        Article article = articleDao.findById(articleIdRequest.getArticleId()).orElse(null);

        if (article != null) {
            return Result.success(ResultCode.GET_ARTICLE_SUCCESS, article);
        } else {
            return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        }
    }

    /**
     * 查看指定分类下的博文业务.
     *
     * @param categoryIdRequest 分类 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> getArticlesByCategoryId(final CategoryIdRequest categoryIdRequest) {
        List<Article> articles = articleDao.findByCategoryId(categoryIdRequest.getCategoryId());

        if (articles != null) {
            return Result.success(ResultCode.SEARCH_ARTICLES_SUCCESS, articles);
        } else {
            return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        }
    }

    /**
     * 分页查询博客业务.
     *
     * @param pageable 分页查询博客请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Page<Article>> getArticlesByPage(final Pageable pageable) {
        Page<Article> articles = articleDao.findAll(pageable);

        if (articles != null) {
            return Result.success(ResultCode.SEARCH_ARTICLES_SUCCESS, articles);
        } else {
            return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
        }
    }

    /**
     * 查看指定用户发布的博文业务.
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> getArticlesByUserId(final UserIdRequest userIdRequest) {
        List<Article> articles = articleDao.findByAuthorId(userIdRequest.getUserId());
        return Result.success(articles);
    }

    /**
     * 博文点赞业务.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> likeArticle(final ArticleIdRequest articleIdRequest) {
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
            } else {
                return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
            }
        } else {
            return Result.fail(ResultCode.NOT_LOGGED_IN);
        }
    }

    /**
     * 博客收藏业务.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> collectArticle(final ArticleIdRequest articleIdRequest) {
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
            } else {
                return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
            }
        } else {
            return Result.fail(ResultCode.NOT_LOGGED_IN);
        }
    }

    /**
     * 统计所有博文的点赞量之和业务.
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getTotalLikesCount() {
        return Result.success(articleDao.getTotalLikesCount());
    }

    /**
     * 查询特定博文点赞的用户业务.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> getLikedUsersById(final ArticleIdRequest articleIdRequest) {
        return Result.success(ResultCode.GET_LIKED_USERS_SUCCESS, articleDao.findLikedUsersById(articleIdRequest.getArticleId()));
    }

    /**
     * 统计所有博文收藏量之和业务.
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getTotalCollectsCount() {
        return Result.success(articleDao.getTotalCollectsCount());
    }

    /**
     * 查询特定博文收藏的用户业务.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> getCollectedUsersById(final ArticleIdRequest articleIdRequest) {
        return Result.success(ResultCode.GET_COLLECTED_USERS_SUCCESS, articleDao.findCollectedUsersById(articleIdRequest.getArticleId()));
    }

    /**
     * 获取特定用户收藏的博文业务.
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Article>> getCollectedArticlesByUserId(final UserIdRequest userIdRequest) {
        return Result.success(articleDao.findCollectedArticlesByUserId(userIdRequest.getUserId()));
    }

    /**
     * 删除博文业务.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Article> deleteArticle(final ArticleIdRequest articleIdRequest) {
        if (currentSubject.isAuthenticated()) {
            Article deletedArticle = articleDao.findById(articleIdRequest.getArticleId()).orElse(null);

            if (deletedArticle != null) {
                articleDao.delete(deletedArticle);

                return Result.success(ResultCode.DELETE_ARTICLE_SUCCESS, deletedArticle);
            } else {
                return Result.fail(ResultCode.ARTICLE_NOT_EXISTS);
            }
        } else {
            return Result.fail(ResultCode.NOT_LOGGED_IN);
        }
    }
}
