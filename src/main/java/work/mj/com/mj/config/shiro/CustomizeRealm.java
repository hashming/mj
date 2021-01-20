package work.mj.com.mj.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

/**
 * 自定义Realm
 * com.example.demo.configuration.shiro
 * CustomizeRealm
 */
public class CustomizeRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 权限认证 暂时不使用  授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 身份认证 登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        获取登录令牌用户名  这个是获取用户名
        String username = (String) token.getPrincipal();
        //得到密码
        String password = new String((char[])token.getCredentials());

        System.out.println(username+"============="+password);
//        获取用户信息
        User user = userService.getByUsername(username);

        //判断
        if (user == null) {
            throw new AuthenticationException();
        }
        //简单身份验证信息  参数：用户输入的用户名，其真正的密码
        return new SimpleAuthenticationInfo(username, user.getPassword(), "pb");
    }
}
