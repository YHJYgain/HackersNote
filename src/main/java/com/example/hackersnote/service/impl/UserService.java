package com.example.hackersnote.service.impl;

import com.example.hackersnote.dao.*;
import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.user.*;
import com.example.hackersnote.result.*;
import com.example.hackersnote.service.IUserService;
import jakarta.annotation.PostConstruct;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户服务实现类
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;
    private Subject currentSubject;
    private String currentUsername;
    private User currentUser;

    /**
     * 初始化用户服务所需资源
     */
    @PostConstruct
    public void init() {
        currentSubject = SecurityUtils.getSubject();
    } // end init()

    /**
     * 注册业务
     *
     * @param registerRequest 注册请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<User> register(RegisterRequest registerRequest) {
        if (!userDao.existsByUsername(registerRequest.getUsername())) {
            User newUser = new User();
            newUser.setUsername(registerRequest.getUsername());

            String salt = "8d78869f470951332959580424d4bf4f"; // 盐值
            String encryptedPassword = new Md5Hash(registerRequest.getUsername(), salt, 2).toString();
            newUser.setPassword(encryptedPassword);

            newUser.setNickname(registerRequest.getNickname());
            newUser.setAvatar(registerRequest.getAvatar());
            newUser.setCreateDate(new Date());
            newUser.setUpdateDate(new Date());
            userDao.save(newUser);

            return Result.success(ResultCode.REGISTER_SUCCESS, newUser);
        } else return Result.fail(ResultCode.REGISTER_FAIL);
    } // end register(registerRequest)

    /**
     * 登录业务
     *
     * @param loginRequest 登录请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<User> login(LoginRequest loginRequest) {
        if (!currentSubject.isAuthenticated()) {
            /* 生成 Token */
            UsernamePasswordToken token = new UsernamePasswordToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword());

            try {
                currentSubject.login(token); // Shiro 自动验证
                currentUsername = (String) currentSubject.getPrincipal();
                currentUser = userDao.findUserByUsername(currentUsername);
                return Result.success(ResultCode.LOGIN_SUCCESS, currentUser);
            } catch (AuthenticationException e) {
                return Result.fail(ResultCode.LOGIN_ERROR); // 登录失败
            }
        } else return Result.fail(ResultCode.ALREADY_LOGGED_IN);
    } // end login(loginRequest)

    /**
     * 统计已注册用户数量业务
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getUsersCount() {
        return Result.success(userDao.count());
    } // end getUsersNumber()

    /**
     * 查看当前用户个人信息业务
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<User> getLoggedUserInfo() {
        if (currentSubject.isAuthenticated()) {
            return Result.success(ResultCode.GET_USER_INFO_SUCCESS, currentUser);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end getLoggedUserInfo()

    /**
     * 查看可公开用户个人信息业务
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<UserInfo> getUserInfo(UserIdRequest userIdRequest) {
        User user = userDao.findById(userIdRequest.getUserId()).orElse(null);

        if (user != null) {
            UserInfo userInfo = new UserInfo(user);

            return Result.success(ResultCode.GET_OTHER_USER_INFO_SUCCESS, userInfo);
        } else return Result.fail(ResultCode.USER_NOT_EXISTS);
    } // end getUserInfo(getUserInfoRequest)

    /**
     * 更新用户业务
     *
     * @param updateUserRequest 更新用户请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<User> updateUser(UpdateUserRequest updateUserRequest) {
        currentUser.setAvatar(updateUserRequest.getAvatar());
        currentUser.setNickname(updateUserRequest.getNickname());
        currentUser.setUpdateDate(new Date());
        userDao.save(currentUser);

        return Result.success(ResultCode.UPDATE_USER_SUCCESS, currentUser);
    } // end updateUser(updateUserRequest)

    /**
     * 验证密码业务
     *
     * @param validatePasswordRequest 验证密码请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Boolean> validatePassword(ValidatePasswordRequest validatePasswordRequest) {
        String password = validatePasswordRequest.getPassword();
        String salt = "8d78869f470951332959580424d4bf4f"; // 盐值
        String encryptedPassword = new Md5Hash(password, salt, 2).toString();
        if (currentUser.getPassword().equals(encryptedPassword)) {
            return Result.success(ResultCode.PASSWORD_CORRECT, true);
        } else return Result.fail(ResultCode.PASSWORD_ERROR, false);
    } // end validatePassword(validatePasswordRequest)

    /**
     * 修改密码业务
     *
     * @param changePasswordRequest 修改密码请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<User> changePassword(ChangePasswordRequest changePasswordRequest) {
        if (currentSubject.isAuthenticated()) {
            /* 验证旧密码是否匹配 */
            String oldPassword = changePasswordRequest.getOldPassword();
            String salt = "8d78869f470951332959580424d4bf4f"; // 盐值
            String encryptedOldPassword = new Md5Hash(oldPassword, salt, 2).toString();
            if (!currentUser.getPassword().equals(encryptedOldPassword)) {
                return Result.fail(ResultCode.CHANGE_PASSWORD_FAIL);
            } else {
                /* 加密新密码并更新用户密码 */
                String newPassword = changePasswordRequest.getNewPassword();
                String encryptedNewPassword = new Md5Hash(newPassword, salt, 2).toString();
                currentUser.setPassword(encryptedNewPassword);
                currentUser.setUpdateDate(new Date());
                userDao.save(currentUser);

                return Result.success(ResultCode.CHANGE_PASSWORD_SUCCESS, currentUser);
            }
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end changePassword(changePasswordRequest)

    /**
     * 关注用户业务
     *
     * @param followUserRequest 关注用户请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<String> followUser(FollowUserRequest followUserRequest) {
        if (currentSubject.isAuthenticated()) {
            currentUsername = (String) currentSubject.getPrincipal();
            currentUser = userDao.findUserByUsername(currentUsername);
            User followedUser = userDao.findById(followUserRequest.getFollowedUserId()).orElse(null);

            if (followedUser != null) {
                if (!currentUser.getFollowedUsers().contains(followedUser)) {
                    currentUser.getFollowedUsers().add(followedUser);
                    userDao.save(currentUser);

                    String followedUsername = followedUser.getUsername();
                    String msg = "”" + currentUsername + "“"
                            + "关注"
                            + "”" + followedUsername + "“";
                    return Result.success(ResultCode.FOLLOW_SUCCESS, msg);
                } else {
                    currentUser.getFollowedUsers().remove(followedUser);
                    userDao.save(currentUser);

                    String followedUsername = followedUser.getUsername();
                    String msg = "”" + currentUsername + "“"
                            + "取消关注"
                            + "”" + followedUsername + "“";
                    return Result.success(ResultCode.CANCEL_FOLLOW_SUCCESS, msg);
                }
            } else return Result.fail(ResultCode.USER_NOT_EXISTS);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end followUser(followUserRequest)

    /**
     * 当前登录用户是否关注特定用户业务
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Boolean> isFollowForUserId(UserIdRequest userIdRequest) {
        currentUsername = (String) currentSubject.getPrincipal();
        currentUser = userDao.findUserByUsername(currentUsername);
        User followedUser = userDao.findById(userIdRequest.getUserId()).orElse(null);

        if (followedUser != null) {
            if (currentUser.getFollowedUsers().contains(followedUser)) {
                return Result.success(ResultCode.ALREADY_FOLLOW, true);
            } else return Result.success(ResultCode.NOT_FOLLOW, false);
        } else return Result.fail(ResultCode.USER_NOT_EXISTS);
    } // end isFollowForUserId(userIdRequest)

    /**
     * 统计特定用户粉丝数量业务
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Long> getFollowersCountForUserId(UserIdRequest userIdRequest) {
        return Result.success(userDao.countFollowersForUserId(userIdRequest.getUserId()));
    } // end getFollowersCountForUserId(userIdRequest)

    /**
     * 获取特定用户的粉丝集合业务
     *
     * @param userIdRequest 用户 ID 请求体
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<Set<User>> getFollowersForUserId(UserIdRequest userIdRequest) {
        Set<User> followers = userDao.findFollowersForUserId(userIdRequest.getUserId());

        return Result.success(ResultCode.GET_FOLLOWS_SUCCESS, followers);
    } // end getFollowersForUserId(userIdRequest)

    /**
     * 退出登录业务
     *
     * @return 结果体
     */
    @Override
    @Transactional
    public Result<User> logout() {
        if (currentSubject.isAuthenticated()) {
            SecurityUtils.getSubject().logout(); // 登出
            return Result.success(ResultCode.LOGOUT_SUCCESS, currentUser);
        } else return Result.fail(ResultCode.NOT_LOGGED_IN);
    } // end logout()

} // end class UserService
