package com.example.hackersnote.realm;

import com.example.hackersnote.dao.UserDao;
import com.example.hackersnote.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义用户认证与授权的 Shiro Realm 实现.
 * <p>
 * 该类负责从数据库中加载用户数据以进行身份验证和授权处理。它继承了 {@link AuthorizingRealm}，
 * 并重写了用于身份验证的 {@code doGetAuthenticationInfo} 方法和用于授权的 {@code doGetAuthorizationInfo} 方法.
 * </p>
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 用户数据访问对象，用于从数据库中检索用户信息.
     */
    @Autowired
    private UserDao userDao;

    /**
     * 实现身份验证逻辑.
     * <p>
     * 根据传入的 {@link AuthenticationToken}，使用用户名从数据库检索用户信息，
     * 若用户存在，则返回包含用户名、密码和盐值的 {@link SimpleAuthenticationInfo} 对象，
     * 否则抛出 {@link UnknownAccountException} 异常.
     * </p>
     *
     * @param token 身份验证令牌，包含登录时传入的用户名和密码
     * @return 包含认证信息的 {@link AuthenticationInfo} 对象
     * @throws AuthenticationException 当身份验证失败时抛出此异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userDao.findUserByUsername(username);

        if (user == null) {
            throw new UnknownAccountException(); // 用户不存在
        }

        String salt = "8d78869f470951332959580424d4bf4f"; // 带上 salt 值
        return new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(salt),
                getName()
        );
    }

    /**
     * 实现授权逻辑.
     * <p>
     * 此方法用于处理用户授权信息的加载。目前未实现，如需角色或权限授权信息，可在此方法中进行扩展.
     * </p>
     *
     * @param principalCollection 包含已认证的用户信息的 {@link PrincipalCollection}
     * @return 返回授权信息的 {@link AuthorizationInfo} 对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principalCollection) {
        return null;
    }
}
