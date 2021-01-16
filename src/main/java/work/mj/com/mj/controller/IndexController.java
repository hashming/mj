package work.mj.com.mj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/login")
    public String getlogin(@RequestParam(name = "name")String name,@RequestParam(name = "password")String password,Model model){
        Boolean qualifications = loginUser.judgeOuth(name,password);
        if (qualifications){
            return "index";
        }
        return null;
    }
}
