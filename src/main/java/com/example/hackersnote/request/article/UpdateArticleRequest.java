package com.example.hackersnote.request.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 更新博文请求体封装类.
 * <p>
 * 用于封装更新博文的请求体，其中包含博文的基本信息和分类 ID 列表.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest {
    /**
     * 博文 ID，用于标识需要更新的博文.
     */
    private Long articleId;
    /**
     * 更新后的博文标题.
     */
    private String title;
    /**
     * 更新后的封面图片 URL.
     */
    private String featuredImage;
    /**
     * 更新后的博文描述.
     */
    private String description;
    /**
     * 更新后的博文内容.
     */
    private String content;
    /**
     * 更新后的分类 ID 列表.
     */
    private List<Long> categoryIds;
}
