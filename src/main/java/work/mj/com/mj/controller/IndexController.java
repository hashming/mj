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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class IndexController {

    @Autowired
    private LoginUser loginUser;

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        //遍历cookie 寻找名字是token的那个
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    Register register = registerService.findByToken(token);
                    if (register != null) {
                        request.getSession().setAttribute("name", register);
                    }
                    break;
                }
            }
        return "index";
    }

    //登录方法 登录界面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //shiro更改后的
    @PostMapping("/doLogin")
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        if (user != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            System.out.println(token);
            try {
//                如果账号密码都正确不会抛出异常
//                shiro 认证 当认证失败时会抛出异常
                //try catch 处理掉异常
                subject.login(token);

                String idtoken = UUID.randomUUID().toString();
                //存入数据库
                registerService.setRegister(user.getUsername(), idtoken);
                response.addCookie(new Cookie("token", idtoken));
                return "redirect:/";
            } catch (Exception e) {
                return "passwordError";
            }
        }
        //否则跳转到密码错误页面
        return "passwordError";
    }
}
