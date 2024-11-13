package com.example.hackersnote.web;

import com.example.hackersnote.entity.Category;
import com.example.hackersnote.request.article.ArticleIdRequest;
import com.example.hackersnote.request.category.CategoryIdRequest;
import com.example.hackersnote.request.category.CategoryNameRequest;
import com.example.hackersnote.result.Result;
import com.example.hackersnote.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 分类控制器类.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    /**
     * CategoryService 服务，负责分类相关的业务逻辑处理.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * URL：localhost:8125/category/createCategory
     * 创建分类.
     *
     * @param categoryNameRequest 分类名称请求体
     * @return 统一封装的结果
     */
    @PostMapping("/createCategory")
    public Result<Category> createCategory(@Valid @RequestBody final CategoryNameRequest categoryNameRequest) {
        return categoryService.createCategory(categoryNameRequest);
    }

    /**
     * URL：localhost:8125/category/getCategoryDetail
     * 查询分类信息.
     *
     * @param categoryIdRequest 分类 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getCategoryDetail")
    public Result<Category> getCategoryDetail(@Valid @RequestBody final CategoryIdRequest categoryIdRequest) {
        return categoryService.getCategoryDetail(categoryIdRequest);
    }

    /**
     * URL：localhost:8125/category/getAllCategories
     * 获取所有分类.
     *
     * @return 统一封装的结果
     */
    @GetMapping("/getAllCategories")
    public Result<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * URL：localhost:8125/category/getCategoriesByArticleId
     * 查询特定博文所属分类.
     *
     * @param articleIdRequest 博文 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getCategoriesByArticleId")
    public Result<List<Category>> getCategoriesByArticleId(@Valid @RequestBody final ArticleIdRequest articleIdRequest) {
        return categoryService.getCategoriesByArticleId(articleIdRequest);
    }
}
