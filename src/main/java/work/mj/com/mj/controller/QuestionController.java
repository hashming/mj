package work.mj.com.mj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.mj.com.mj.dto.QuestionDTO;
import work.mj.com.mj.service.QuestionService;
//问题详情
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 问题详情
     * @param id  问题对应的id
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}