package com.example.hackersnote.result;

import lombok.*;

import java.io.Serializable;

/**
 * 返回的结果类：统一封装 API 返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private int code; // 状态码
    private String message; // 结果描述信息
    private T data; // 返回的结果

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    } // end Result(code, message)

    /**
     * 静态泛型方法：通用成功（带返回结果）
     *
     * @param <T>  结果类型
     * @param data 结果
     * @return 统一封装的结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    } // end Result<T> success(data)

    /**
     * 静态泛型方法：通用成功（返回结果为 null）
     *
     * @param resultCode 结果枚举类对象
     * @return 统一封装的结果
     */
    public static <T> Result<T> success(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    } // end Result<T> success(resultCode)

    /**
     * 静态泛型方法：自定义成功（带返回结果）
     *
     * @param resultCode 结果枚举类对象
     * @param <T>        结果类型
     * @param data       结果
     * @return 统一封装的结果
     */
    public static <T> Result<T> success(ResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 静态泛型方法：通用失败（返回结果为 null，无需调用 setData）
     *
     * @param <T> 结果类型
     * @return 统一封装结果
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getMessage());
        return result;
    } // end Result<T> fail()

    /**
     * 静态泛型方法：其他在枚举类中的失败（返回结果为 null）
     *
     * @param resultCode 结果枚举类对象
     * @param <T>        结果类型
     * @return 统一封装的结果
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    } // end Result<T> fail(resultCode)

    /**
     * 静态泛型方法：自定义失败（带返回结果）
     *
     * @param resultCode 结果枚举类对象
     * @param <T>        结果类型
     * @param data       结果
     * @return 统一封装的结果
     */
    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    } // end Result<T> fail(resultCode, data)

} // end Result<T>
