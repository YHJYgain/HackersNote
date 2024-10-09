package com.example.hackersnote.service;

import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.article.*;
import com.example.hackersnote.request.category.CategoryIdRequest;
import com.example.hackersnote.request.user.UserIdRequest;
import com.example.hackersnote.result.Result;
import org.springframework.data.domain.*;

import java.util.*;

/**
 * 博文服务接口类
 */
public interface IArticleService {
    Result<Article> publishArticle(PublishArticleRequest publishArticleRequest);
    Result<Article> updateArticle(UpdateArticleRequest updateArticleRequest);
    Result<List<Article>> searchArticles(SearchRequest searchRequest);
    Result<Long> getArticlesCount();
    Result<Article> getArticleDetail(ArticleIdRequest articleIdRequest);
    Result<List<Article>> getArticlesByCategoryId(CategoryIdRequest categoryIdRequest);
    Result<Page<Article>> getArticlesByPage(Pageable pageable);
    Result<List<Article>> getArticlesByUserId(UserIdRequest userIdRequest);
    Result<Set<User>> likeArticle(ArticleIdRequest articleIdRequest);
    Result<Set<User>> collectArticle(ArticleIdRequest articleIdRequest);
    Result<Long> getTotalLikesCount();
    Result<Set<User>> getLikedUsersById(ArticleIdRequest articleIdRequest);
    Result<Long> getTotalCollectsCount();
    Result<Set<User>> getCollectedUsersById(ArticleIdRequest articleIdRequest);
    Result<List<Article>> getCollectedArticlesByUserId(UserIdRequest userIdRequest);
    Result<Article> deleteArticle(ArticleIdRequest articleIdRequest);
} // end interface IArticleService
