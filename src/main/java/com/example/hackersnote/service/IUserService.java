package com.example.hackersnote.service;

import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.user.*;
import com.example.hackersnote.result.Result;

import java.util.Set;

/**
 * 用户服务接口类
 */
public interface IUserService {
     Result<User> register(RegisterRequest registerRequest);
     Result<User> login(LoginRequest loginRequest);
     Result<Long> getUsersCount();
     Result<User> getLoggedUserInfo();
     Result<UserInfo> getUserInfo(UserIdRequest userIdRequest);
     Result<User> updateUser(UpdateUserRequest updateUserRequest);
     Result<Boolean> validatePassword(ValidatePasswordRequest validatePasswordRequest);
     Result<User> changePassword(ChangePasswordRequest changePasswordRequest);
     Result<String> followUser(FollowUserRequest followUserRequest);
     Result<Boolean> isFollowForUserId(UserIdRequest userIdRequest);
     Result<Long> getFollowersCountForUserId(UserIdRequest userIdRequest);
     Result<Set<User>> getFollowersForUserId(UserIdRequest userIdRequest);
     Result<User> logout();
} // end interface IUserService
