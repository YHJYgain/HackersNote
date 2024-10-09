package com.example.hackersnote.request.article;

import lombok.*;

/**
 * 博文 ID 请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleIdRequest {
    private Long articleId;
} // end class ArticleIdRequest
