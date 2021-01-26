package work.mj.com.mj.config.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法执行了");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登陆的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        Session session = subject.getSession();
        String token = (String) session.getAttribute("token");
        if (token != null) {

        }

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPermission());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证方法执行了");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();

        //从数据库中查找对应的User信息
        User user = userService.getByUsername(username);

        if (user == null) {
            throw new AuthenticationException();
        }

        //这里可以加一个session存储
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("name",user);

        //这里把user放入  授权的时候就可以用了
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
