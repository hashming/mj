package work.mj.com.mj.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加Shiro的内置过滤器
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        //授权
//        hashMap.put("/publish","perms[user:add]");
        //authc是要开启登录验证
        hashMap.put("/","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        //登录成功跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/");
        //未登录跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthor");

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    //创建realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect 用来整合Shiro thymeleaf
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
