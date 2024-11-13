package com.example.hackersnote.service;

import com.example.hackersnote.entity.Category;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.category.CategoryIdRequest;
import com.example.hackersnote.request.category.CategoryNameRequest;
import com.example.hackersnote.result.Result;

import java.util.List;

/**
 * 分类服务接口类.
 * <p>
 * 该接口定义了所有与分类相关的服务方法。包括分类的创建、查询分类详细信息、获取所有分类、根据博文ID获取分类等操作。
 * 各方法的返回值为 {@link Result} 类型，表示操作的结果及其状态信息.
 * </p>
 */
public interface ICategoryService {
    /**
     * 创建新的分类.
     *
     * @param categoryNameRequest 分类名称请求参数
     * @return 返回创建的分类信息及操作结果
     */
    Result<Category> createCategory(CategoryNameRequest categoryNameRequest);

    /**
     * 获取分类的详细信息.
     *
     * @param categoryIdRequest 分类 ID 请求参数
     * @return 返回指定分类的详细信息
     */
    Result<Category> getCategoryDetail(CategoryIdRequest categoryIdRequest);

    /**
     * 获取所有分类.
     *
     * @return 返回所有分类的列表
     */
    Result<List<Category>> getAllCategories();

    /**
     * 根据博文 ID 获取分类列表.
     *
     * @param articleIdRequest 博文 ID 请求参数
     * @return 返回指定博文相关的分类列表
     */
    Result<List<Category>> getCategoriesByArticleId(ArticleIdRequest articleIdRequest);
}
