package com.example.hackersnote.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类 ID 请求体封装类.
 * <p>
 * 用于封装分类 ID 的请求体，便于在请求中传递分类的标识符.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryIdRequest {
    /**
     * 分类 ID，用于标识特定的分类.
     */
    private Long categoryId;
}
