package com.example.hackersnote.request.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关键词查询博文请求体封装类.
 * <p>
 * 用于封装关键词查询博文的请求体，其中包含搜索关键词.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    /**
     * 搜索关键词，用于查询博文.
     */
    private String query;
}
