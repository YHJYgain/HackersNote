package com.example.hackersnote.web;

import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.article.*;
import com.example.hackersnote.request.category.*;
import com.example.hackersnote.request.user.*;
import com.example.hackersnote.result.*;
import com.example.hackersnote.service.impl.ArticleService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

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
    void publishArticle() {
        /* 设置发布博文请求参数 */
        PublishArticleRequest publishArticleRequest = new PublishArticleRequest();
        publishArticleRequest.setTitle("testTitle");
        publishArticleRequest.setFeaturedImage("testFeaturedImage");
        publishArticleRequest.setDescription("testDescription");
        publishArticleRequest.setContent("testContent");
        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);
        categoryIds.add(2L);
        publishArticleRequest.setCategoryIds(categoryIds);

        /* 设置期望的结果 */
        Result<Article> expectedResult = new Result<>();
        when(articleService.publishArticle(any(PublishArticleRequest.class))).thenReturn(expectedResult);

        Result<Article> actualResult = articleController.publishArticle(publishArticleRequest);

        assertEquals(expectedResult, actualResult);
    } // end publishArticle()

    @Test
    void updateArticle() {
        /* 设置更新博文请求参数 */
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest();
        updateArticleRequest.setArticleId(7L);
        updateArticleRequest.setTitle("newTitle");
        updateArticleRequest.setFeaturedImage("newFeaturedImage");
        updateArticleRequest.setDescription("newDescription");
        updateArticleRequest.setContent("newContent");
        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(3L);
        updateArticleRequest.setCategoryIds(categoryIds);

        /* 设置期望的结果 */
        Result<Article> expectedResult = new Result<>();
        when(articleService.updateArticle(any(UpdateArticleRequest.class))).thenReturn(expectedResult);

        Result<Article> actualResult = articleController.updateArticle(updateArticleRequest);

        assertEquals(expectedResult, actualResult);
    } // end updateArticle()

    @Test
    void searchArticles() {
        /* 设置搜索博文请求参数 */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setQuery("图书");

        /* 设置期望的结果 */
        Result<List<Article>> expectedResult = new Result<>();
        when(articleService.searchArticles(any(SearchRequest.class))).thenReturn(expectedResult);

        Result<List<Article>> actualResult = articleController.searchArticles(searchRequest);

        assertEquals(expectedResult, actualResult);
    } // end searchArticles()

    @Test
    void getArticlesCount() {
        /* 设置期望的结果 */
        Result<Long> expectedResult = new Result<>();
        when(articleService.getArticlesCount()).thenReturn(expectedResult);

        Result<Long> actualResult = articleController.getArticlesCount();

        assertEquals(expectedResult, actualResult);
    } // end getArticlesCount()

    @Test
    void getArticleDetail() {
        /* 设置查看博文请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(1L);

        /* 设置期望的结果 */
        Result<Article> expectedResult = new Result<>();
        when(articleService.getArticleDetail(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<Article> actualResult = articleController.getArticleDetail(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getArticleDetail()

    @Test
    void getArticlesByCategoryId() {
        /* 设置请求参数 */
        CategoryIdRequest categoryIdRequest = new CategoryIdRequest();
        categoryIdRequest.setCategoryId(1L);

        /* 设置期望的结果 */
        Result<List<Article>> expectedResult = new Result<>();
        when(articleService.getArticlesByCategoryId(any(CategoryIdRequest.class))).thenReturn(expectedResult);

        Result<List<Article>> actualResult = articleController.getArticlesByCategoryId(categoryIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getArticlesByCategoryId()

    @Test
    void getArticlesByUserId() {
        /* 设置请求参数 */
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L);

        /* 设置期望的结果 */
        Result<List<Article>> expectedResult = new Result<>();
        when(articleService.getArticlesByUserId(any(UserIdRequest.class))).thenReturn(expectedResult);

        Result<List<Article>> actualResult = articleController.getArticlesByUserId(userIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getArticlesByUserId()

    @Test
    void likeArticle() {
        /* 设置请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(2L);

        /* 设置期望的结果 */
        Result<Set<User>> expectedResult = new Result<>();
        when(articleService.likeArticle(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<Set<User>> actualResult = articleController.likeArticle(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end likeArticle()

    @Test
    void collectArticle() {
        /* 设置请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(2L);

        /* 设置期望的结果 */
        Result<Set<User>> expectedResult = new Result<>();
        when(articleService.collectArticle(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<Set<User>> actualResult = articleController.collectArticle(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end collectArticle()

    @Test
    void getTotalLikesCount() {
        /* 设置期望的结果 */
        Result<Long> expectedResult = new Result<>();
        when(articleService.getTotalLikesCount()).thenReturn(expectedResult);

        Result<Long> actualResult = articleController.getTotalLikesCount();

        assertEquals(expectedResult, actualResult);
    } // end getTotalLikesCount()

    @Test
    void getLikedUsersById() {
        /* 设置请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(1L);

        /* 设置期望的结果 */
        Result<Set<User>> expectedResult = new Result<>();
        when(articleService.getLikedUsersById(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<Set<User>> actualResult = articleController.getLikedUsersById(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getLikedUsersById()

    @Test
    void getTotalCollectsCount() {
        /* 设置期望的结果 */
        Result<Long> expectedResult = new Result<>();
        when(articleService.getTotalCollectsCount()).thenReturn(expectedResult);

        Result<Long> actualResult = articleController.getTotalCollectsCount();

        assertEquals(expectedResult, actualResult);
    } // end getTotalCollectsCount()

    @Test
    void getCollectedUsersById() {
        /* 设置请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(2L);

        /* 设置期望的结果 */
        Result<Set<User>> expectedResult = new Result<>();
        when(articleService.getCollectedUsersById(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<Set<User>> actualResult = articleController.getCollectedUsersById(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getCollectedUsersById()

    @Test
    void getCollectedArticlesByUserId() {
        /* 设置请求参数 */
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L);

        /* 设置期望的结果 */
        Result<List<Article>> expectedResult = new Result<>();
        when(articleService.getCollectedArticlesByUserId(any(UserIdRequest.class))).thenReturn(expectedResult);

        Result<List<Article>> actualResult = articleController.getCollectedArticlesByUserId(userIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getCollectedArticlesByUserId()

    @Test
    void deleteArticle() {
        /* 设置请求参数 */
        ArticleIdRequest articleIdRequest = new ArticleIdRequest();
        articleIdRequest.setArticleId(7L);

        /* 设置期望的结果 */
        Result<Article> expectedResult = new Result<>();
        when(articleService.deleteArticle(any(ArticleIdRequest.class))).thenReturn(expectedResult);

        Result<Article> actualResult = articleController.deleteArticle(articleIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end deleteArticle()
} // end class ArticleControllerTest