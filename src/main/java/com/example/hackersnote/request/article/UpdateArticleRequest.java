package com.example.hackersnote.request.article;

import lombok.*;

import java.util.List;

/**
 * 更新博文请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest {

    private Long articleId;
    private String title;
    private String featuredImage;
    private String description;
    private String content;
    private List<Long> categoryIds;

} // end class UpdateArticleRequest
