package com.example.hackersnote.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类名称请求体封装类.
 * <p>
 * 用于封装分类名称的请求体，便于在请求中传递分类的名称信息.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNameRequest {
    /**
     * 分类名称，用于标识或查找特定的分类.
     */
    private String categoryName;
}
