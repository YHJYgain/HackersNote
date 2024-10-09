package com.example.hackersnote.realm;

import com.example.hackersnote.dao.UserDao;
import com.example.hackersnote.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户访问安全数据类
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userDao.findUserByUsername(username);

        if (user == null) {
            throw new UnknownAccountException(); // 用户不存在
        }

        String salt = "8d78869f470951332959580424d4bf4f"; // 带上 salt 值
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(salt),
                getName()
        );
        return authenticationInfo;
    } // end doGetAuthenticationInfo()

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    } // end doGetAuthorizationInfo()

} // end class UserRealm
