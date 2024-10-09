package com.example.hackersnote.request.article;

import lombok.*;

/**
 * 关键词查询博文请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private String query;
} // end class SearchRequest
