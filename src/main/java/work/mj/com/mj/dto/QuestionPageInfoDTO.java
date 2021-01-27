package work.mj.com.mj.dto;

import lombok.Data;
import work.mj.com.mj.pojo.Question;
import work.mj.com.mj.pojo.Register;

@Data
public class QuestionPageInfoDTO {
    Register register;
    Question question;
}
