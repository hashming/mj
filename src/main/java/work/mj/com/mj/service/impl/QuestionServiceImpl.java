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
     * 分页查找全部问题列表
     * @param page  当前页数
     * @param size  当前页面大小
     * @return
     */
    @Override
    public PaginationDTO list(Integer page,Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //问题总数
        Integer totalCount = questionMapper.count();

        //把
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page<1){
            page=1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);
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

    /**
     * 分页查找特定用户的问题列表
     * @param registerId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer registerId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;

        Integer totalCount = questionMapper.countByUserId(registerId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        if (offset<0){
            offset = 0;
        }
        List<Question> questions = questionMapper.listByUserId(registerId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

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

    /**
     * 根据问题ID查找问题详情
     * @param id
     * @return
     */
    @Override
    public QuestionDTO getById(Integer id) {

        Boolean ce = questionMapper.updateViewCountById(id);
        System.out.println(ce);

        Question question = questionMapper.getById(id);

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        Register register = registerMapper.findById(question.getCreator());
        questionDTO.setRegister(register);
        return questionDTO;
    }

    /**
     * 判断id是否存在，如果存在就更新，如果不存在就创建
     * @param question
     */
    @Override
    public void createOrUpdate(Question question) {
        //判断是否存在对应的id
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        } else {
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }


}
