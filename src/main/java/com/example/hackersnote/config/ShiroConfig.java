package com.example.hackersnote.config;

import com.example.hackersnote.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro 配置类：整合 SecurityManager、Realm、SHA-256 加密等
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建 Realm，并添加密码比较器
     *
     * @return Realm
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    } // end UserRealm userRealm()

    /**
     * 创建密码匹配器.
     *
     * @return 散列凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5"); // 使用 md5 加密
        credentialsMatcher.setHashIterations(2); // 迭代次数
        return credentialsMatcher;
    } // end HashedCredentialsMatcher hashedCredentialsMatcher()

    /**
     * 创建 SecurityManager，并绑定 Realm
     *
     * @param userRealm Realm
     * @return Shiro 安全管理器
     */
    @Bean
    public DefaultSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    } // end securityManager(userRealm)

    /**
     * 开启对 Shiro 的注解的支持（如 @RequiresRoles,@RequiresPermissions），不开启的话权限验证就会失效
     *
     * @param securityManager SecurityManager
     * @return 授权属性通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    } // end authorizationAttributeSourceAdvisor()

} // end class ShiroConfig
