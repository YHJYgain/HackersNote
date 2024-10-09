package com.example.hackersnote.request.article;

import lombok.*;

import java.util.List;

/**
 * 发布博文请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishArticleRequest {
    private String title;
    private String featuredImage;
    private String description;
    private String content;
    private List<Long> categoryIds;
} // end class CreateArticleRequest
