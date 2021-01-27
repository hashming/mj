package work.mj.com.mj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import work.mj.com.mj.service.LoginUser;
import work.mj.com.mj.service.QuestionService;
import work.mj.com.mj.service.RegisterService;
import work.mj.com.mj.service.UserService;


@Controller
public class IndexController {

    @Autowired
    private LoginUser loginUser;

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private QuestionService questionService;

}
