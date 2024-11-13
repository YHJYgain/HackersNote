package com.example.hackersnote.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回的结果类：统一封装 API 返回结果.
 * <p>
 * 用于统一封装 API 的返回结果，包含状态码、描述信息以及返回的数据.
 * 该类的静态方法提供了不同类型的响应方式，例如成功或失败的结果，以及带有具体数据的返回.
 * </p>
 *
 * @param <T> 结果的数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 状态码，通常为 HTTP 状态码，表示请求的执行结果.
     */
    private int code;
    /**
     * 结果描述信息，通常描述状态码对应的含义.
     */
    private String message;
    /**
     * 返回的结果数据，通常是请求的具体响应内容.
     */
    private T data;

    /**
     * 构造一个只包含状态码和描述信息的结果.
     * @param statusCode 状态码
     * @param msg        结果描述信息
     */
    public Result(final int statusCode, final String msg) {
        this.code = statusCode;
        this.message = msg;
    }

    /**
     * 通用成功返回结果（带返回数据）.
     * @param <T>  结果的数据类型
     * @param data 返回的数据
     * @return 封装成功结果的 {@link Result} 对象
     */
    public static <T> Result<T> success(final T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 通用成功返回结果（返回结果为 null）.
     * @param <T>        结果的数据类型
     * @param resultCode 结果枚举类对象
     * @return 封装成功结果的 {@link Result} 对象
     */
    public static <T> Result<T> success(final ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 自定义成功返回结果（带返回数据）。
     * @param resultCode 结果枚举类对象
     * @param <T>        结果的数据类型
     * @param data       返回的数据
     * @return 封装成功结果的 {@link Result} 对象
     */
    public static <T> Result<T> success(final ResultCode resultCode, final T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 通用失败返回结果（不带数据）.
     * @param <T> 结果的数据类型
     * @return 封装失败结果的 {@link Result} 对象
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getMessage());
        return result;
    }

    /**
     * 其他在枚举类中的失败返回结果（不带数据）.
     * @param resultCode 结果枚举类对象
     * @param <T>        结果的数据类型
     * @return 封装失败结果的 {@link Result} 对象
     */
    public static <T> Result<T> fail(final ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 自定义失败返回结果（带返回数据）.
     * @param resultCode 结果枚举类对象
     * @param <T>        结果的数据类型
     * @param data       返回的数据
     * @return 封装失败结果的 {@link Result} 对象
     */
    public static <T> Result<T> fail(final ResultCode resultCode, final T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }
}
