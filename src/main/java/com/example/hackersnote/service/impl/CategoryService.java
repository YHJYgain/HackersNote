package com.example.hackersnote.service.impl;

import com.example.hackersnote.dao.CategoryDao;
import com.example.hackersnote.entity.Category;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.category.*;
import com.example.hackersnote.result.*;
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
 * 分类服务类
 */
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;
    private Subject currentSubject;

    /**
     * 初始化用户服务所需资源
     */
    @PostConstruct
    public void init() {
        currentSubject = SecurityUtils.getSubject();
    } // end init()

    /**
     * 创建分类业务
     *
     * @param categoryNameRequest 分类名称请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Category> createCategory(CategoryNameRequest categoryNameRequest) {
        if (currentSubject.isAuthenticated()) {
            if (!categoryDao.existsByName(categoryNameRequest.getCategoryName())) {
                Category newCategory = new Category();
                newCategory.setName(categoryNameRequest.getCategoryName());
                categoryDao.save(newCategory);

                return Result.success(ResultCode.CREATE_CATEGORY_SUCCESS, newCategory);
            } else return Result.fail(ResultCode.CREATE_CATEGORY_FAIL);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end createCategory(createCategoryRequest)

    /**
     * 查询分类信息业务
     *
     * @param categoryIdRequest 分类 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Category> getCategoryDetail(CategoryIdRequest categoryIdRequest) {
        Category category = categoryDao.findById(categoryIdRequest.getCategoryId()).orElse(null);
        if (category != null) {
            return Result.success(ResultCode.GET_CATEGORY_SUCCESS, category);
        } else return Result.fail(ResultCode.CATEGORY_NOT_EXISTS);
    } // end getCategoryDetail(categoryIdRequest)

    /**
     * 获取所有分类业务
     *
     * @return 结果体
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
    } // end getAllCategories()

    /**
     * 查询特定博文所属分类业务
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<List<Category>> getCategoriesByArticleId(ArticleIdRequest articleIdRequest) {
        return Result.success(categoryDao.findByCategorizedArticlesId(articleIdRequest.getArticleId()));
    } // end getCategoriesByArticleId(articleIdRequest)

} // end class CategoryService
