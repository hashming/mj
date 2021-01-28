package work.mj.com.mj.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import work.mj.com.mj.dao.RegisterMapper;
import work.mj.com.mj.dto.PaginationDTO;
import work.mj.com.mj.dto.QuestionPageInfoDTO;
import work.mj.com.mj.pojo.Question;
import work.mj.com.mj.pojo.Register;
import work.mj.com.mj.pojo.RegisterExample;
import work.mj.com.mj.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.util.List;

//个人问题列表
@Controller
public class ProfileController {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private QuestionService questionService;

    /**
     * 右上角下拉的逻辑 展示个人的问题列表
     * @param request
     * @param action
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        Register register = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    RegisterExample registerExample = new RegisterExample();
                    registerExample.createCriteria().andTokenEqualTo(token);
                    List<Register> registers = registerMapper.selectByExample(registerExample);
                    register = registers.get(0);
                    if (registers.size() != 0) {
                        request.getSession().setAttribute("name",registers.get(0));
                    }
                    break;
                }
            }
        }

        if (register == null) {
            return "redirect:/";
        }

        //前端有个判断高亮显示的东西
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PageInfo<Question> pageInfo = questionService.list(register.getId(), page, size);
        model.addAttribute("pagination", pageInfo);
        return "profile";
    }
}