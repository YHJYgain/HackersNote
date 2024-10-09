package com.example.hackersnote.service;

import com.example.hackersnote.entity.Category;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.category.*;
import com.example.hackersnote.result.Result;

import java.util.List;

/**
 * 分类服务接口类
 */
public interface ICategoryService {
    Result<Category> createCategory(CategoryNameRequest categoryNameRequest);
    Result<Category> getCategoryDetail(CategoryIdRequest categoryIdRequest);
    Result<List<Category>> getAllCategories();
    Result<List<Category>> getCategoriesByArticleId(ArticleIdRequest articleIdRequest);
} // end interface ICategoryService
