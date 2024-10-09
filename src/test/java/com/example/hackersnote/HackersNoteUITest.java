package com.example.hackersnote;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HackersNoteUITest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chromedriver.exe"); // 设置 Chrome 驱动程序路径

        /* 设置 Chrome 浏览器启动参数 */
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options); // 创建 ChromeDriver 实例
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 设置显式等待

        driver.get("http://localhost:8126"); // 打开网站

//        testRegistration(driver, wait); // 测试注册功能
        testLogin(driver, wait); // 测试登录功能
//        testFollowUser(driver, wait); // 测试关注功能
//        testPublishArticle(driver, wait); // 测试发布博文功能
//        testEditArticle(driver, wait); // 测试编辑博文功能
//        testDeleteArticle(driver, wait); // 测试删除博文功能
//        testSearchArticle(driver, wait); // 测试搜索博文功能
//        testLikeAndCollect(driver, wait); // 测试点赞/收藏功能
        testLogout(driver, wait); // 测试登出功能

//        driver.quit(); // 关闭浏览器
    } // end main()


    private static void testRegistration(WebDriver driver, WebDriverWait wait) {
        /* 找到注册链接并点击 */
        WebElement registerLink = driver.findElement(By.id("register"));
        registerLink.click();

        /* 填写注册表单 */
        WebElement usernameField = driver.findElement(By.id("username")); // 用户名
        WebElement nicknameField = driver.findElement(By.id("nickname")); // 昵称
        WebElement passwordField = driver.findElement(By.id("password")); // 密码
        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword")); // 确认密码
        WebElement registerButton = driver.findElement(By.id("registerButton")); // 注册按钮
        usernameField.sendKeys("WZY4");
        nicknameField.sendKeys("Re.Gin4");
        passwordField.sendKeys("WZY4");
        confirmPasswordField.sendKeys("WZY4");
        registerButton.click();

        /* 验证注册成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("Registration test passed.");
        } else {
            System.out.println("Registration test failed.");
        }
    } // end testRegistration()

    private static void testLogin(WebDriver driver, WebDriverWait wait) {
        /* 找到登录链接并点击 */
        WebElement loginLink = driver.findElement(By.id("login"));
        loginLink.click();

        /* 填写登录表单 */
        WebElement usernameField = driver.findElement(By.id("username")); // 用户名
        WebElement passwordField = driver.findElement(By.id("password")); // 密码
        WebElement loginButton = driver.findElement(By.id("loginButton")); // 登录按钮
        usernameField.sendKeys("WZY2");
        passwordField.sendKeys("WZY2");
        loginButton.click();

        /* 验证登录成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("Login test passed.");
        } else {
            System.out.println("Login test failed.");
        }
    } // end testLogin()

    private static void testFollowUser(WebDriver driver, WebDriverWait wait) {
        /* 找到博文链接并点击 */
        WebElement articleLink = driver.findElement(By.linkText("Vue3 引用 md-editor（修改）"));
        articleLink.click();

        /* 找到博主主页按钮并点击 */
        WebElement userInfoButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("userInfo")));
        userInfoButton.click();

        /* 找到关注按钮并点击 */
        WebElement followButton = driver.findElement(By.className("followUser-link"));
        followButton.click();

        /* 验证关注成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("FollowUser test passed.");
        } else {
            System.out.println("FollowUser test failed.");
        }
    } // end testFollowUser()

    private static void testPublishArticle(WebDriver driver, WebDriverWait wait) {
        /* 找到发布博文链接并点击 */
        WebElement publishArticleLink = driver.findElement(By.id("publishArticle"));
        publishArticleLink.click();

        /* 填写博文表单 */
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title"))); // 标题
        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description"))); // 描述
        titleField.sendKeys("Selenium 自动化测试");
        descriptionField.sendKeys("测试 Selenium");
        WebElement publishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("publish"))); // 发布按钮
        publishButton.click();

        /* 验证注册成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("PublishArticle test passed.");
        } else {
            System.out.println("PublishArticle test failed.");
        }
    } // end testPublishArticle()

    private static void testEditArticle(WebDriver driver, WebDriverWait wait) {
        /* 找到博文链接并点击 */
        WebElement articleLink = driver.findElement(By.linkText("Android 音乐播放器"));
        articleLink.click();

        /* 找到编辑博文按钮并点击 */
        WebElement editArticleButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editArticle")));
        editArticleButton.click();

        /* 编辑博文表单 */
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title"))); // 标题
        titleField.sendKeys("Android 音乐播放器（修改）");
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("save"))); // 保存按钮
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", saveButton);

        /* 验证编辑成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("EditArticle test passed.");
        } else {
            System.out.println("EditArticle test failed.");
        }
    } // end testEditArticle()

    private static void testDeleteArticle(WebDriver driver, WebDriverWait wait) {
        /* 找到博文链接并点击 */
        WebElement articleLink = driver.findElement(By.linkText("Node.js 安装教程"));
        articleLink.click();

        /* 找到编辑博文按钮并点击 */
        WebElement editArticleButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editArticle")));
        editArticleButton.click();

        /* 找到删除博文按钮并点击 */
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete"))); // 删除按钮
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", deleteButton);

        /* 验证编辑成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("DeleteArticle test passed.");
        } else {
            System.out.println("DeleteArticle test failed.");
        }
    } // end testDeleteArticle()

    private static void testSearchArticle(WebDriver driver, WebDriverWait wait) {
        /* 找到搜索框并输入关键字，回车搜索 */
        WebElement searchField = driver.findElement(By.id("searchArticle"));
        searchField.sendKeys("音乐");
        searchField.sendKeys(Keys.ENTER);

        /* 验证编辑成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("SearchArticle test passed.");
        } else {
            System.out.println("SearchArticle test failed.");
        }
    } // end testSearchArticle()

    private static void testLikeAndCollect(WebDriver driver, WebDriverWait wait) {
        /* 找到点赞按钮并点击 */
        WebElement likeField = driver.findElement(By.id("like"));
        likeField.click();

        /* 找到收藏按钮并点击 */
        WebElement collectField = driver.findElement(By.id("collect"));
        collectField.click();

        /* 验证编辑成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("LikeAndCollect test passed.");
        } else {
            System.out.println("LikeAndCollect test failed.");
        }
    } // end testLikeAndCollect()

    private static void testLogout(WebDriver driver, WebDriverWait wait) {
        /* 找到登录链接并点击 */
        WebElement logoutLink = driver.findElement(By.id("logout"));
        logoutLink.click();

        /* 验证注册成功的提示信息是否出现 */
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("el-message--success")));
        if (successMessage.isDisplayed()) {
            System.out.println("Logout test passed.");
        } else {
            System.out.println("Logout test failed.");
        }
    } // end testLogout()
} // end class HackersNoteUITest
