package com.example.hackersnote.request.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发布博文请求体封装类.
 * <p>
 * 用于封装包含发布博文所需数据的请求体，包括标题、封面图片、描述、内容和分类 ID.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishArticleRequest {
    /**
     * 博文标题.
     */
    private String title;
    /**
     * 博文封面图片的 URL.
     */
    private String featuredImage;
    /**
     * 博文的简要描述.
     */
    private String description;
    /**
     * 博文的详细内容.
     */
    private String content;
    /**
     * 分类 ID 列表，用于指定博文所属的分类.
     */
    private List<Long> categoryIds;
}
