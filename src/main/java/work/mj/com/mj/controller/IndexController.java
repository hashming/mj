package work.mj.com.mj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.LoginUser;
import work.mj.com.mj.service.UserService;

@Controller
public class IndexController {

    @Autowired
    private LoginUser loginUser;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //浏览器传过来的值放在model中
        model.addAttribute("name", name);
        return "index";//返回首页
    }

    //shiro更改后的
    @PostMapping("/doLogin")
    public String doLogin(User user) {
        if (user != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
//                如果账号密码都正确不会抛出异常
//                shiro 认证 当认证失败时会抛出异常
                //try catch 处理掉异常
                subject.login(token);
                return "index";
            } catch (Exception e) {
                return "passwordError";
            }
        }
        //否则跳转到密码错误页面
        return "passwordError";
    }


    //登录方法 登录界面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
