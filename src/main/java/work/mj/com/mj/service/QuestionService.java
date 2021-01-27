package work.mj.com.mj.service;

import com.github.pagehelper.PageInfo;
import work.mj.com.mj.dto.PaginationDTO;
import work.mj.com.mj.dto.QuestionDTO;
import work.mj.com.mj.dto.QuestionPageInfoDTO;
import work.mj.com.mj.pojo.Question;

public interface QuestionService {

    PageInfo<Question> list(Integer page, Integer size);

    PageInfo<Question> list(Integer registerId, Integer page, Integer size);

    QuestionDTO getById(Integer id);

    public void createOrUpdate(Question question);
}
