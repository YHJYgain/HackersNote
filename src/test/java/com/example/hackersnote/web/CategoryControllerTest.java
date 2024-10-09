package com.example.hackersnote.web;

import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.article.*;
import com.example.hackersnote.request.category.*;
import com.example.hackersnote.result.Result;
import com.example.hackersnote.service.impl.CategoryService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    } // end setUp()

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    } // end tearDown()

    @Test
    void createCategory() {
        /* 设置请求参数 */
        CategoryNameRequest categoryNameRequest = new CategoryNameRequest();
        categoryNameRequest.setCategoryName("测试");

        /* 设置期望的结果 */
        Result<Category> expectedResult = new Result<>();
        when(categoryService.createCategory(any(CategoryNameRequest.class))).thenReturn(expectedResult);

        Result<Category> actualResult = categoryController.createCategory(categoryNameRequest);

        assertEquals(expectedResult, actualResult);
    } // end createCategory()

    @Test
    void getCategoryDetail() {
        /* 设置请求参数 */
        CategoryIdRequest categoryIdRequest = new CategoryIdRequest();
        categoryIdRequest.setCategoryId(1L);

        /* 设置期望的结果 */
        Result<Category> expectedResult = new Result<>();
        when(categoryService.getCategoryDetail(any(CategoryIdRequest.class))).thenReturn(expectedResult);

        Result<Category> actualResult = categoryController.getCategoryDetail(categoryIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getCategoryDetail()

    @Test
    void getAllCategories() {
        /* 设置期望的结果 */
        Result<List<Category>> expectedResult = new Result<>();
        when(categoryService.getAllCategories()).thenReturn(expectedResult);

        Result<List<Category>> actualResult = categoryController.getAllCategories();

        assertEquals(expectedResult, actualResult);
    } // end getAllCategories()

    @Test
    void getCategoriesByArticleId() {
        /* 设置请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(1L);

        /* 设置期望的结果 */
        Result<List<Category>> expectedResult = new Result<>();
        when(categoryService.getCategoriesByArticleId(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<List<Category>> actualResult = categoryController.getCategoriesByArticleId(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getCategoriesByArticleId()
} // end class CategoryControllerTest