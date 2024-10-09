package com.example.hackersnote.request.category;

import lombok.*;

/**
 * 分类 ID 请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryIdRequest {
    private Long categoryId;
} // end class CategoryIdRequest
