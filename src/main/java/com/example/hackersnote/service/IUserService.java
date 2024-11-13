package com.example.hackersnote.service;

import com.example.hackersnote.entity.User;
import com.example.hackersnote.entity.UserInfo;
import com.example.hackersnote.request.user.ChangePasswordRequest;
import com.example.hackersnote.request.user.FollowUserRequest;
import com.example.hackersnote.request.user.LoginRequest;
import com.example.hackersnote.request.user.RegisterRequest;
import com.example.hackersnote.request.user.UpdateUserRequest;
import com.example.hackersnote.request.user.UserIdRequest;
import com.example.hackersnote.request.user.ValidatePasswordRequest;
import com.example.hackersnote.result.Result;

import java.util.Set;

/**
 * 用户服务接口类.
 * <p>
 * 该接口定义了所有与用户相关的服务方法，包括用户注册、登录、信息更新、密码验证、关注功能等。
 * 每个方法返回一个 {@link Result} 类型的对象，表示操作的结果以及相关信息.
 * </p>
 */
public interface IUserService {
    /**
     * 用户注册.
     *
     * @param registerRequest 注册请求参数，包含用户名、密码等信息
     * @return 返回注册结果及注册后的用户信息
     */
    Result<User> register(RegisterRequest registerRequest);

    /**
     * 用户登录.
     *
     * @param loginRequest 登录请求参数，包含用户名和密码
     * @return 返回登录结果及登录后的用户信息
     */
    Result<User> login(LoginRequest loginRequest);

    /**
     * 获取用户总数.
     *
     * @return 返回用户总数
     */
    Result<Long> getUsersCount();

    /**
     * 获取已登录用户信息.
     *
     * @return 返回当前登录用户的信息
     */
    Result<User> getLoggedUserInfo();

    /**
     * 获取指定用户的信息.
     *
     * @param userIdRequest 用户 ID 请求参数
     * @return 返回指定用户的详细信息
     */
    Result<UserInfo> getUserInfo(UserIdRequest userIdRequest);

    /**
     * 更新用户信息.
     *
     * @param updateUserRequest 更新用户的请求参数
     * @return 返回更新后的用户信息
     */
    Result<User> updateUser(UpdateUserRequest updateUserRequest);

    /**
     * 验证密码是否正确.
     *
     * @param validatePasswordRequest 密码验证请求参数
     * @return 返回密码验证结果
     */
    Result<Boolean> validatePassword(ValidatePasswordRequest validatePasswordRequest);

    /**
     * 修改用户密码.
     *
     * @param changePasswordRequest 修改密码的请求参数
     * @return 返回修改密码后的用户信息
     */
    Result<User> changePassword(ChangePasswordRequest changePasswordRequest);

    /**
     * 关注指定用户.
     *
     * @param followUserRequest 关注用户请求参数，包含要关注的目标用户 ID
     * @return 返回关注结果信息
     */
    Result<String> followUser(FollowUserRequest followUserRequest);

    /**
     * 检查是否已关注指定用户.
     *
     * @param userIdRequest 用户 ID 请求参数
     * @return 返回是否已关注的结果
     */
    Result<Boolean> isFollowForUserId(UserIdRequest userIdRequest);

    /**
     * 获取指定用户的粉丝数.
     *
     * @param userIdRequest 用户 ID 请求参数
     * @return 返回指定用户的粉丝数量
     */
    Result<Long> getFollowersCountForUserId(UserIdRequest userIdRequest);

    /**
     * 获取指定用户的所有粉丝.
     *
     * @param userIdRequest 用户 ID 请求参数
     * @return 返回指定用户的所有粉丝
     */
    Result<Set<User>> getFollowersForUserId(UserIdRequest userIdRequest);

    /**
     * 用户登出.
     *
     * @return 返回登出结果
     */
    Result<User> logout();
}
