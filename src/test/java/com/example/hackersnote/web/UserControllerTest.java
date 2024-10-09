package com.example.hackersnote.web;

import com.example.hackersnote.entity.*;
import com.example.hackersnote.request.user.*;
import com.example.hackersnote.result.*;
import com.example.hackersnote.service.impl.UserService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    } // end setUp()

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    } // end tearDown()

    @Test
    void register() {
        /* 设置注册请求参数 */
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        registerRequest.setNickname("testUser");
        registerRequest.setAvatar("testImg");

        /* 设置期望的结果 */
        Result<User> expectedResult = new Result<>();
        when(userService.register(any(RegisterRequest.class))).thenReturn(expectedResult);

        Result<User> actualResult = userController.register(registerRequest);

        assertEquals(expectedResult, actualResult);
    } // end register()

    @Test
    void login() {
        /* 设置登录请求参数 */
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test");
        loginRequest.setPassword("test");

        /* 设置期望的结果 */
        Result<User> expectedResult = new Result<>();
        when(userService.login(any(LoginRequest.class))).thenReturn(expectedResult);

        Result<User> actualResult = userController.login(loginRequest);

        assertEquals(expectedResult, actualResult);
    } // end login()

    @Test
    void getUsersCount() {
        /* 设置期望的结果 */
        Result<Long> expectedResult = new Result<>();
        when(userService.getUsersCount()).thenReturn(expectedResult);

        Result<Long> actualResult = userController.getUsersCount();

        assertEquals(expectedResult, actualResult);
    } // end getUsersCount()

    @Test
    void getLoggedUserInfo() {
        /* 设置期望的结果 */
        Result<User> expectedResult = new Result<>();
        when(userService.getLoggedUserInfo()).thenReturn(expectedResult);

        Result<User> actualResult = userController.getLoggedUserInfo();

        assertEquals(expectedResult, actualResult);
    } // end getLoggedUserInfo()

    @Test
    void getUserInfo() {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L); // 设置用户 ID 请求参数

        /* 设置期望的结果 */
        Result<UserInfo> expectedResult = new Result<>();
        when(userService.getUserInfo(any(UserIdRequest.class))).thenReturn(expectedResult);

        Result<UserInfo> actualResult = userController.getUserInfo(userIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getUserInfo()

    @Test
    void updateUser() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        // 设置更新用户请求参数
        updateUserRequest.setAvatar("newAvatar");
        updateUserRequest.setNickname("newNickname");

        /* 设置期望的结果 */
        Result<User> expectedResult = new Result<>();
        when(userService.updateUser(any(UpdateUserRequest.class))).thenReturn(expectedResult);

        Result<User> actualResult = userController.updateUser(updateUserRequest);

        assertEquals(expectedResult, actualResult);
    } // end updateUser()

    @Test
    void validatePassword() {
        /* 设置验证密码请求参数 */
        ValidatePasswordRequest validatePasswordRequest = new ValidatePasswordRequest();
        validatePasswordRequest.setPassword("test");

        /* 设置期望的结果 */
        Result<Boolean> expectedResult = new Result<>();
        when(userService.validatePassword(any(ValidatePasswordRequest.class))).thenReturn(expectedResult);

        Result<Boolean> actualResult = userController.validatePassword(validatePasswordRequest);

        assertEquals(expectedResult, actualResult);
    } // end validatePassword()

    @Test
    void changePassword() {
        /* 设置修改密码请求参数 */
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setOldPassword("test");
        changePasswordRequest.setNewPassword("newTest");

        /* 设置期望的结果 */
        Result<User> expectedResult = new Result<>();
        when(userService.changePassword(any(ChangePasswordRequest.class))).thenReturn(expectedResult);

        Result<User> actualResult = userController.changePassword(changePasswordRequest);

        assertEquals(expectedResult, actualResult);
    } // end changePassword()

    @Test
    void followUser() {
        /* 设置关注用户请求参数 */
        FollowUserRequest followUserRequest = new FollowUserRequest();
        followUserRequest.setFollowedUserId(1L);

        /* 设置期望的结果 */
        Result<String> expectedResult = new Result<>();
        when(userService.followUser(any(FollowUserRequest.class))).thenReturn(expectedResult);

        Result<String> actualResult = userController.followUser(followUserRequest);

        assertEquals(expectedResult, actualResult);
    } // end followUser()

    @Test
    void isFollowForUserId() {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L); // 设置用户 ID 请求参数

        /* 设置期望的结果 */
        Result<Boolean> expectedResult = new Result<>();
        when(userService.isFollowForUserId(any(UserIdRequest.class))).thenReturn(expectedResult);

        Result<Boolean> actualResult = userController.isFollowForUserId(userIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end isFollowForUserId()

    @Test
    void getFollowersCountForUserId() {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L); // 设置用户 ID 请求参数

        /* 设置期望的结果 */
        Result<Long> expectedResult = new Result<>();
        when(userService.getFollowersCountForUserId(any(UserIdRequest.class))).thenReturn(expectedResult);

        Result<Long> actualResult = userController.getFollowersCountForUserId(userIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getFollowersCountForUserId()

    @Test
    void getFollowersForUserId() {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L); // 设置用户 ID 请求参数

        /* 设置期望的粉丝列表 */
        Set<User> expectedFollowers = new HashSet<>();
        Result<Set<User>> expectedResult = new Result<>();
        expectedResult.setData(expectedFollowers);

        /* 设置期望的结果 */
        when(userService.getFollowersForUserId(any(UserIdRequest.class))).thenReturn(expectedResult);
        Result<Set<User>> actualResult = userController.getFollowersForUserId(userIdRequest);

        assertEquals(expectedResult, actualResult);
    } // end getFollowersForUserId()

    @Test
    void logout() {
        /* 设置期望的结果 */
        Result<User> expectedResult = new Result<>();
        when(userService.logout()).thenReturn(expectedResult);

        Result<User> actualResult = userController.logout();

        assertEquals(expectedResult, actualResult);
    } // end logout()
} // end class UserControllerTest