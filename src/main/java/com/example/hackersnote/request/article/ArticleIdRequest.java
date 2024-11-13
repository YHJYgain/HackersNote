package com.example.hackersnote.request.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博文 ID 请求体封装类.
 * <p>
 * 用于封装包含博文 ID 的请求体数据，在请求接口时传递特定的博文 ID.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleIdRequest {
    /**
     * 博文的唯一标识符.
     */
    private Long articleId;
}
