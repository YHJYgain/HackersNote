package com.example.hackersnote.request.category;

import lombok.*;

/**
 * 分类名称请求体封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNameRequest {

    private String categoryName;

} // end class CreateCategoryRequest
