package work.mj.com.mj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.mj.com.mj.pojo.Register;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.LoginUser;
import work.mj.com.mj.service.RegisterService;
import work.mj.com.mj.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private LoginUser loginUser;

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        //看看session在不在
//        System.out.println(request.getSession().getAttribute("name"));
        return "index";
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //浏览器传过来的值放在model中
        model.addAttribute("name", name);
        return "index";//返回首页
    }

    //登录方法 登录界面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //shiro更改后的
    @PostMapping("/doLogin")
    public String doLogin(User user, HttpServletRequest request) {
        if (user != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
//                如果账号密码都正确不会抛出异常
//                shiro 认证 当认证失败时会抛出异常
                //try catch 处理掉异常
                subject.login(token);

                //存入数据库
                registerService.setRegister(user.getUsername());

                //如果没有异常的情况下就会写入name到Session中。
                request.getSession().setAttribute("name",user.getUsername());
                System.out.println(request.getSession().getAttribute("name"));
                return "redirect:/";
            } catch (Exception e) {
                return "passwordError";
            }
        }
        //否则跳转到密码错误页面
        return "passwordError";
    }
}
