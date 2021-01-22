package work.mj.com.mj.service;

import work.mj.com.mj.dto.PaginationDTO;
import work.mj.com.mj.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    PaginationDTO list(Integer page, Integer size);
}
