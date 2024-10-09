package com.example.hackersnote.web;

import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.article.*;
import com.example.hackersnote.request.category.*;
import com.example.hackersnote.request.user.*;
import com.example.hackersnote.result.*;
import com.example.hackersnote.service.impl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 博文控制器类
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * URL：localhost:8125/article/publishArticle
     * 发布博文
     *
     * @param publishArticleRequest 发布博文请求体
     * @return 统一封装的结果
     */
    @PostMapping("/publishArticle")
    public Result<Article> publishArticle(@Valid @RequestBody PublishArticleRequest publishArticleRequest) {
        return articleService.publishArticle(publishArticleRequest);
    }

    /**
     * URL：localhost:8125/article/updateArticle
     * 更新博文
     *
     * @param updateArticleRequest 更新博文请求体
     * @return 统一封装的结果
     */
    @PutMapping("/updateArticle")
    public Result<Article> updateArticle(@Valid @RequestBody UpdateArticleRequest updateArticleRequest) {
        return articleService.updateArticle(updateArticleRequest);
    }

    /**
     * URL：localhost:8125/article/searchArticles
     * 关键字查询博文
     *
     * @param searchRequest 关键词查询博文请求体
     * @return 统一封装的结果
     */
    @PostMapping("/searchArticles")
    public Result<List<Article>> searchArticles(@Valid @RequestBody SearchRequest searchRequest) {
        return articleService.searchArticles(searchRequest);
    }

    /**
     * URL：localhost:8125/article/getArticlesCount
     * 统计已发布博文数量
     *
     * @return 统一封装的结果
     */
    @GetMapping("/getArticlesCount")
    public Result<Long> getArticlesCount() {
        return articleService.getArticlesCount();
    }

    /**
     * URL：localhost:8125/article/getArticleDetail
     * 查看博文信息
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getArticleDetail")
    public Result<Article> getArticleDetail(@Valid @RequestBody ArticleIdRequest articleIdRequest) {
        return articleService.getArticleDetail(articleIdRequest);
    }

    /**
     * URL：localhost:8125/article/getArticlesByCategoryId
     * 查看指定分类下的博文
     *
     * @param categoryIdRequest 分类 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getArticlesByCategoryId")
    public Result<List<Article>> getArticlesByCategoryId(
            @Valid @RequestBody CategoryIdRequest categoryIdRequest) {
        return articleService.getArticlesByCategoryId(categoryIdRequest);
    }

    /**
     * URL：localhost:8125/article/getArticlesByPage
     * 分页查询博客
     *
     * @param pageable 分页查询博客请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getArticlesByPage")
    public Result<Page<Article>> getArticlesByPage(@Valid @RequestBody Pageable pageable) {
        return articleService.getArticlesByPage(pageable);
    }

    /**
     * URL：localhost:8125/article/getArticlesByUserId
     * 查看指定用户发布的博文
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getArticlesByUserId")
    public Result<List<Article>> getArticlesByUserId(@Valid @RequestBody UserIdRequest userIdRequest) {
        return articleService.getArticlesByUserId(userIdRequest);
    }

    /**
     * URL：localhost:8125/article/likeArticle
     * 点赞博文
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PutMapping("/likeArticle")
    public Result<Set<User>> likeArticle(@Valid @RequestBody ArticleIdRequest articleIdRequest) {
        return articleService.likeArticle(articleIdRequest);
    }

    /**
     * URL：localhost:8125/article/collectArticle
     * 收藏博文
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PutMapping("/collectArticle")
    public Result<Set<User>> collectArticle(@Valid @RequestBody ArticleIdRequest articleIdRequest) {
        return articleService.collectArticle(articleIdRequest);
    }

    /**
     * URL：localhost:8125/article/getTotalLikesCount
     * 统计所有博文的点赞量之和
     *
     * @return 统一封装的结果
     */
    @GetMapping("/getTotalLikesCount")
    public Result<Long> getTotalLikesCount() {
        return articleService.getTotalLikesCount();
    }

    /**
     * URL：localhost:8125/article/getLikedUsersById
     * 查询特定博文点赞的用户
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getLikedUsersById")
    public Result<Set<User>> getLikedUsersById(@Valid @RequestBody ArticleIdRequest articleIdRequest) {
        return articleService.getLikedUsersById(articleIdRequest);
    }

    /**
     * URL：localhost:8125/article/getTotalCollectsCount
     * 统计所有博文收藏量之和
     *
     * @return 统一封装的结果
     */
    @GetMapping("/getTotalCollectsCount")
    public Result<Long> getTotalCollectsCount() {
        return articleService.getTotalCollectsCount();
    }

    /**
     * URL：localhost:8125/article/getCollectedUsersById
     * 查询特定博文收藏的用户
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getCollectedUsersById")
    public Result<Set<User>> getCollectedUsersById(@Valid @RequestBody ArticleIdRequest articleIdRequest) {
        return articleService.getCollectedUsersById(articleIdRequest);
    }

    /**
     * URL：localhost:8125/article/getCollectedArticlesByUserId
     * 获取特定用户收藏的博文
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getCollectedArticlesByUserId")
    public Result<List<Article>> getCollectedArticlesByUserId(@Valid @RequestBody UserIdRequest userIdRequest) {
        return articleService.getCollectedArticlesByUserId(userIdRequest);
    }

    /**
     * URL：localhost:8125/article/deleteArticle
     * 删除博文
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/deleteArticle")
    public Result<Article> deleteArticle(@Valid @RequestBody ArticleIdRequest articleIdRequest) {
        return articleService.deleteArticle(articleIdRequest);
    }
} // end class ArticleController
