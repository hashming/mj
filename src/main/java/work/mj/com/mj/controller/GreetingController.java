package work.mj.com.mj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/nihao")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //浏览器传过来的值放在model中
        model.addAttribute("name", name);
        return "greeting";//需要时才返回
    }
}
