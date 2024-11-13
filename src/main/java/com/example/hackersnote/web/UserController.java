package com.example.hackersnote.web;

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
import com.example.hackersnote.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

/**
 * 用户控制器类.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * UserService 服务，负责用户相关的业务逻辑处理.
     */
    @Autowired
    private UserService userService;

    /**
     * URL：localhost:8125/user/register
     * 注册.
     *
     * @param registerRequest 注册请求体
     * @return 统一封装的结果
     */
    @PutMapping("/register")
    public Result<User> register(@Valid @RequestBody final RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    /**
     * URL：localhost:8125/user/login
     * 登录.
     *
     * @param loginRequest 登录请求体
     * @return 统一封装的结果
     */
    @PostMapping("/login")
    public Result<User> login(@Valid @RequestBody final LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    /**
     * URL：localhost:8125/user/getUsersCount
     * 统计已注册用户数量.
     *
     * @return 统一封装的结果
     */
    @GetMapping("/getUsersCount")
    public Result<Long> getUsersCount() {
        return userService.getUsersCount();
    }

    /**
     * URL：localhost:8125/user/getLoggedUserInfo
     * 查看当前用户个人信息.
     *
     * @return 统一封装的结果
     */
    @GetMapping("/getLoggedUserInfo")
    public Result<User> getLoggedUserInfo() {
        return userService.getLoggedUserInfo();
    }

    /**
     * URL：localhost:8125/user/getUserInfo
     * 查看可公开用户个人信息.
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getUserInfo")
    public Result<UserInfo> getUserInfo(@Valid @RequestBody final UserIdRequest userIdRequest) {
        return userService.getUserInfo(userIdRequest);
    }

    /**
     * URL：localhost:8125/user/updateUser
     * 更新用户.
     *
     * @param updateUserRequest 更新用户请求体
     * @return 统一封装的结果
     */
    @PutMapping("/updateUser")
    public Result<User> updateUser(@Valid @RequestBody final UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    /**
     * URL：localhost:8125/user/validatePassword
     * 验证密码.
     *
     * @param validatePasswordRequest 验证密码请求体
     * @return 统一封装的结果
     */
    @PostMapping("/validatePassword")
    public Result<Boolean> validatePassword(@Valid @RequestBody final ValidatePasswordRequest validatePasswordRequest) {
        return userService.validatePassword(validatePasswordRequest);
    }

    /**
     * URL：localhost:8125/user/changePassword
     * 修改密码.
     *
     * @param changePasswordRequest 修改密码请求体
     * @return 统一封装的结果
     */
    @PutMapping("/changePassword")
    public Result<User> changePassword(@Valid @RequestBody final ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(changePasswordRequest);
    }

    /**
     * URL：localhost:8125/user/followUser
     * 关注用户.
     *
     * @param followUserRequest 关注用户请求体
     * @return 统一封装的结果
     */
    @PutMapping("/followUser")
    public Result<String> followUser(@Valid @RequestBody final FollowUserRequest followUserRequest) {
        return userService.followUser(followUserRequest);
    }

    /**
     * URL：localhost:8125/user/isFollowForUserId
     * 当前登录用户是否关注特定用户.
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/isFollowForUserId")
    public Result<Boolean> isFollowForUserId(@Valid @RequestBody final UserIdRequest userIdRequest) {
        return userService.isFollowForUserId(userIdRequest);
    }

    /**
     * URL：localhost:8125/user/getFollowersCountForUserId
     * 获取指定用户粉丝数.
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getFollowersCountForUserId")
    public Result<Long> getFollowersCountForUserId(@Valid @RequestBody final UserIdRequest userIdRequest) {
        return userService.getFollowersCountForUserId(userIdRequest);
    }

    /**
     * URL：localhost:8125/user/getFollowersForUserId
     * 获取指定用户粉丝列表.
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 统一封装的结果
     */
    @PostMapping("/getFollowersForUserId")
    public Result<Set<User>> getFollowersForUserId(@Valid @RequestBody final UserIdRequest userIdRequest) {
        return userService.getFollowersForUserId(userIdRequest);
    }

    /**
     * URL：localhost:8125/user/logout
     * 登出.
     *
     * @return 统一封装的结果
     */
    @GetMapping("/logout")
    public Result<User> logout() {
        return userService.logout();
    }
}
