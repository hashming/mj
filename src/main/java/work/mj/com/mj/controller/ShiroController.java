package work.mj.com.mj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.mj.com.mj.dto.PaginationDTO;
import work.mj.com.mj.pojo.Register;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.QuestionService;
import work.mj.com.mj.service.RegisterService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class ShiroController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject);

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

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }

    //登录方法 登录界面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //未授权页面
    @RequestMapping("/unAuthor")
    public String unAuthor() {
        return "unAuthor";
    }

    //注销页面
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) {

        request.getSession().removeAttribute("name");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }

    //
    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model, HttpServletResponse response) {
        if (user!=null) {

//            System.out.println(check);
            //获取当前的用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登陆数据
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//            token.setRememberMe(true);
            try {
                subject.login(token);

                String idtoken = UUID.randomUUID().toString();
                //存入register数据库
                registerService.setRegister(user.getUsername(), idtoken);

                Session session = subject.getSession();
                session.setAttribute("token",idtoken);

                response.addCookie(new Cookie("token", idtoken));

                return "redirect:/";
            } catch (UnknownAccountException e) {
                model.addAttribute("msg", "用户名错误");
                return "passwordError";
            } catch (IncorrectCredentialsException e) {
                model.addAttribute("msg", "密码错误");
                return "passwordError";
            }
        }
        return "passwordError";
    }
}
