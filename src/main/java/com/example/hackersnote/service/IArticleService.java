package com.example.hackersnote.service;

import com.example.hackersnote.entity.Article;
import com.example.hackersnote.entity.User;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.article.PublishArticleRequest;
import com.example.hackersnote.request.article.SearchRequest;
import com.example.hackersnote.request.article.UpdateArticleRequest;
import com.example.hackersnote.request.category.CategoryIdRequest;
import com.example.hackersnote.request.user.UserIdRequest;
import com.example.hackersnote.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * 博文服务接口类.
 * <p>
 * 该接口定义了所有与博文相关的服务方法。包括文章的发布、更新、搜索、获取详细信息、点赞、收藏等操作。
 * 各方法的返回值为 {@link Result} 类型，表示操作的结果及其状态信息.
 * </p>
 */
public interface IArticleService {
    /**
     * 发布一篇新的博文.
     *
     * @param publishArticleRequest 博文发布请求参数
     * @return 返回发布成功的博文信息及操作结果
     */
    Result<Article> publishArticle(PublishArticleRequest publishArticleRequest);

    /**
     * 更新一篇博文.
     *
     * @param updateArticleRequest 博文更新请求参数
     * @return 返回更新后的博文信息及操作结果
     */
    Result<Article> updateArticle(UpdateArticleRequest updateArticleRequest);

    /**
     * 搜索博文.
     *
     * @param searchRequest 搜索请求参数
     * @return 返回符合搜索条件的博文列表
     */
    Result<List<Article>> searchArticles(SearchRequest searchRequest);

    /**
     * 获取博文总数.
     *
     * @return 返回博文总数
     */
    Result<Long> getArticlesCount();

    /**
     * 获取博文详细信息.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回指定博文的详细信息
     */
    Result<Article> getArticleDetail(ArticleIdRequest articleIdRequest);

    /**
     * 根据分类 ID 获取博文列表
     *
     * @param categoryIdRequest 分类 ID 请求参数
     * @return 返回指定分类下的博文列表
     */
    Result<List<Article>> getArticlesByCategoryId(CategoryIdRequest categoryIdRequest);

    /**
     * 分页获取博文列表.
     *
     * @param pageable 分页请求参数
     * @return 返回分页的博文列表
     */
    Result<Page<Article>> getArticlesByPage(Pageable pageable);

    /**
     * 根据用户 ID 获取博文列表.
     *
     * @param userIdRequest 用户 ID 请求参数
     * @return 返回指定用户发布的博文列表
     */
    Result<List<Article>> getArticlesByUserId(UserIdRequest userIdRequest);

    /**
     * 点赞博文.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回点赞的用户集合
     */
    Result<Set<User>> likeArticle(ArticleIdRequest articleIdRequest);

    /**
     * 收藏博文.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回收藏的用户集合
     */
    Result<Set<User>> collectArticle(ArticleIdRequest articleIdRequest);

    /**
     * 获取所有博文的点赞总数.
     *
     * @return 返回博文的点赞总数
     */
    Result<Long> getTotalLikesCount();

    /**
     * 获取指定博文的所有点赞用户.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回点赞该博文的用户集合
     */
    Result<Set<User>> getLikedUsersById(ArticleIdRequest articleIdRequest);

    /**
     * 获取所有博文的收藏总数.
     *
     * @return 返回博文的收藏总数
     */
    Result<Long> getTotalCollectsCount();

    /**
     * 获取指定博文的所有收藏用户.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回收藏该博文的用户集合
     */
    Result<Set<User>> getCollectedUsersById(ArticleIdRequest articleIdRequest);

    /**
     * 获取指定用户收藏的博文.
     *
     * @param userIdRequest 用户 ID 请求参数
     * @return 返回指定用户收藏的博文列表
     */
    Result<List<Article>> getCollectedArticlesByUserId(UserIdRequest userIdRequest);

    /**
     * 删除指定博文.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回删除后的操作结果
     */
    Result<Article> deleteArticle(ArticleIdRequest articleIdRequest);
}
