package work.mj.com.mj.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.mj.com.mj.dao.QuestionMapper;
import work.mj.com.mj.dao.RegisterMapper;
import work.mj.com.mj.dto.QuestionDTO;
import work.mj.com.mj.pojo.Question;
import work.mj.com.mj.pojo.Register;
import work.mj.com.mj.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionDTO> list() {

        List<Question> questions = questionMapper.list();

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //把用户信息拼接到QuestionDto中
        for (Question question : questions) {
            Register user = registerMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setRegister(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
