package work.mj.com.mj.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro配置类
 * com.example.demo.configuration.shiro
 * ShiroConfiguration
 *
 */
@Configuration
public class ShiroConfiguration {

    @Bean("customizeRealm")
    public Realm realm() {
        return new CustomizeRealm();
    }

    @Bean("sessionManager")
    public SessionManager sessionManager() {
        return new DefaultWebSessionManager();
    }

    @Bean("securityManager")
    public SecurityManager securityManager(SessionManager sessionManager, Realm customizeRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(customizeRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置securityManager
        filterFactoryBean.setSecurityManager(securityManager);
        //增加请求地址的认证
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap());
        //未登录跳转页面
        filterFactoryBean.setLoginUrl("/login");
        //登录成功跳转页面
        filterFactoryBean.setSuccessUrl("/index");
        return filterFactoryBean;
    }

    private Map<String, String> filterChainDefinitionMap() {
        //anon 不需要认证
        //authc 需要认证
        //当访问时是从上往下认证的，当URL匹配到第一个的时候,不会继续向下匹配了
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/doLogin", "anon");
        //这里我把主页的权限放开
        filterChainDefinitionMap.put("/", "anon");
        //对其他的资源开启权限认证
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
        return filterChainDefinitionMap;
    }

}