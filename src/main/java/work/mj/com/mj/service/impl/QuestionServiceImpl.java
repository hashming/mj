package work.mj.com.mj.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.mj.com.mj.dao.QuestionMapper;
import work.mj.com.mj.dao.RegisterMapper;
import work.mj.com.mj.dto.PaginationDTO;
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

    /**
     * 分页查找
     * @param page  当前页数
     * @param size  当前页面大小
     * @return
     */
    @Override
    public PaginationDTO list(Integer page,Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        //问题总数
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        if (page<1){
            page=1;
        }

        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);
        //截取一段数据
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        //把用户信息拼接到QuestionDto中
        for (Question question : questions) {
            Register user = registerMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setRegister(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }



}
