package com.example.hackersnote.result;

/**
 * 结果状态码的枚举类：统一配置结果状态码
 */
public enum ResultCode {

    /* 通用结果状态码 */
    SUCCESS(200, "成功"),
    FAIL(-1, "失败"),

    /* 常用结果状态码 */
    CREATED(201, "资源创建成功"),
    UPDATED(202, "资源更新成功"),
    DELETED(204, "资源删除成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权访问"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    CONFLICT(409, "资源冲突"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    /* 用户结果状态码 */
    REGISTER_SUCCESS(1000, "注册成功"),
    REGISTER_FAIL(1001, "注册失败，该用户已存在"),
    NOT_LOGGED_IN(1002, "请登录"),
    LOGIN_SUCCESS(1003, "登录成功"),
    ALREADY_LOGGED_IN(1004, "已登录，无需重复登录"),
    LOGIN_ERROR(1005, "登录失败，用户名或密码错误"),
    USER_NOT_EXISTS(1006, "该用户不存在"),
    GET_USER_INFO_SUCCESS(1007, "获取用户信息成功"),
    GET_OTHER_USER_INFO_SUCCESS(1008, "获取其他用户信息成功"),
    UPDATE_USER_SUCCESS(1009, "用户更新成功"),
    PASSWORD_CORRECT(1010, "密码正确"),
    PASSWORD_ERROR(1011, "密码错误"),
    CHANGE_PASSWORD_SUCCESS(1012, "密码修改成功"),
    CHANGE_PASSWORD_FAIL(1013, "密码修改失败，原密码错误"),
    FOLLOW_SUCCESS(1014, "关注成功"),
    CANCEL_FOLLOW_SUCCESS(1015, "取消关注成功"),
    ALREADY_FOLLOW(1016, "已关注"),
    NOT_FOLLOW(1017, "未关注"),
    GET_FOLLOWS_SUCCESS(1018, "获取粉丝列表成功"),
    DELETE_USER_SUCCESS(1019, "用户删除成功"),
    LOGOUT_SUCCESS(1020, "退出成功"),

    /* 分类结果状态码 */
    CREATE_CATEGORY_SUCCESS(1101, "分类创建成功"),
    CREATE_CATEGORY_FAIL(1102, "分类创建失败，该分类已存在"),
    CATEGORY_NOT_EXISTS(1103, "该分类不存在"),
    CHANGE_CATEGORY_NAME_SUCCESS(1104, "分类名称修改成功"),
    DELETE_CATEGORY_SUCCESS(1105, "分类删除成功"),
    GET_CATEGORY_SUCCESS(1106, "获取分类信息成功"),
    GET_CATEGORY_LIST_SUCCESS(1107, "获取分类列表成功"),
    CATEGORY_LIST_NOT_EXISTS(1108, "获取失败，分类列表为空"),

    /* 博文结果状态码 */
    PUBLISH_ARTICLE_SUCCESS(1201, "博文发布成功"),
    UPDATE_ARTICLE_SUCCESS(1202, "博文更新成功"),
    ARTICLE_NOT_EXISTS(1203, "博文不存在"),
    GET_ARTICLE_SUCCESS(1204, "获取博文信息成功"),
    SEARCH_ARTICLES_SUCCESS(1205, "查询博文成功"),
    LIKED_SUCCESS(1206, "点赞成功"),
    UNLIKED_SUCCESS(1207, "取消点赞成功"),
    GET_LIKED_USERS_SUCCESS(1208, "获取博文所有点赞用户成功"),
    COLLECTED_SUCCESS(1209, "收藏成功"),
    UNCOLLECTED_SUCCESS(1210, "取消收藏成功"),
    GET_COLLECTED_USERS_SUCCESS(1211, "获取博文所有收藏用户成功"),
    DELETE_ARTICLE_SUCCESS(1212, "博文删除成功");


    private final int code; // 状态码
    private final String message; // 结果描述信息

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    } // end ResultCode(code, message)

    public int getCode() {
        return code;
    } // end getCode()

    public String getMessage() {
        return message;
    } // end getMessage()

} // end enum ResultCode
