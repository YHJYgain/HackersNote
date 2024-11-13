package com.example.hackersnote.service.impl;

import com.example.hackersnote.dao.CategoryDao;
import com.example.hackersnote.entity.Category;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.category.CategoryIdRequest;
import com.example.hackersnote.request.category.CategoryNameRequest;
import com.example.hackersnote.result.Result;
import com.example.hackersnote.result.ResultCode;
import com.example.hackersnote.service.ICategoryService;
import jakarta.annotation.PostConstruct;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类服务类，处理与分类相关的业务逻辑.
 */
@Service
public class CategoryService implements ICategoryService {
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
     * 初始化分类服务所需资源，在服务启动时设置当前用户的主体信息.
     */
    @PostConstruct
    public void init() {
        currentSubject = SecurityUtils.getSubject();
    }

    /**
     * 创建分类，如果当前用户已认证且分类名称不存在，则创建一个新的分类.
     *
     * @param categoryNameRequest 分类名称请求体，包含要创建的分类名称
     * @return 结果体，包含操作是否成功以及创建的分类信息
     */
    @Override
    @Transactional
    public Result<Category> createCategory(final CategoryNameRequest categoryNameRequest) {
        if (currentSubject.isAuthenticated()) {
            if (!categoryDao.existsByName(categoryNameRequest.getCategoryName())) {
                Category newCategory = new Category();
                newCategory.setName(categoryNameRequest.getCategoryName());
                categoryDao.save(newCategory);

                return Result.success(ResultCode.CREATE_CATEGORY_SUCCESS, newCategory);
            } else {
                return Result.fail(ResultCode.CREATE_CATEGORY_FAIL);
            }
        } else {
            return Result.fail(ResultCode.NOT_LOGGED_IN);
        }
    }

    /**
     * 查询分类信息，根据分类 ID 查找分类信息.
     *
     * @param categoryIdRequest 分类 ID 请求体，包含要查询的分类 ID
     * @return 结果体，包含操作是否成功以及查找到的分类信息
     */
    @Override
    @Transactional
    public Result<Category> getCategoryDetail(final CategoryIdRequest categoryIdRequest) {
        Category category = categoryDao.findById(categoryIdRequest.getCategoryId()).orElse(null);
        if (category != null) {
            return Result.success(ResultCode.GET_CATEGORY_SUCCESS, category);
        } else {
            return Result.fail(ResultCode.CATEGORY_NOT_EXISTS);
        }
    }

    /**
     * 获取所有分类，查询所有已存在的分类.
     *
     * @return 结果体，包含操作是否成功以及所有分类的列表
     */
    @Override
    @Transactional
    public Result<List<Category>> getAllCategories() {
        Iterable<Category> categoryIterable = categoryDao.findAll();
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryIterable) {
            categories.add(category);
        }

        if (!categories.isEmpty()) {
            return Result.success(ResultCode.GET_CATEGORY_LIST_SUCCESS, categories);
        } else {
            return Result.fail(ResultCode.CATEGORY_LIST_NOT_EXISTS);
        }
    }

    /**
     * 查询特定博文所属分类，根据博文 ID 查询该博文所归属的所有分类.
     *
     * @param articleIdRequest 博文 ID 请求体，包含要查询的博文 ID
     * @return 结果体，包含操作是否成功以及查询到的分类列表
     */
    @Override
    @Transactional
    public Result<List<Category>> getCategoriesByArticleId(final ArticleIdRequest articleIdRequest) {
        return Result.success(categoryDao.findByCategorizedArticlesId(articleIdRequest.getArticleId()));
    }
}
