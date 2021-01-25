package work.mj.com.mj.service;

import work.mj.com.mj.dto.PaginationDTO;
import work.mj.com.mj.dto.QuestionDTO;

public interface QuestionService {

    PaginationDTO list(Integer page, Integer size);

    PaginationDTO list(Integer registerId, Integer page, Integer size);

    QuestionDTO getById(Integer id);
}
