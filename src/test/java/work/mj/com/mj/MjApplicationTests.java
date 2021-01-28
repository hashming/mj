package work.mj.com.mj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.mj.com.mj.dao.QuestionMapper;
import work.mj.com.mj.dao.RegisterMapper;
import work.mj.com.mj.dao.UserMapper;
import work.mj.com.mj.pojo.Question;
import work.mj.com.mj.pojo.QuestionExample;
import work.mj.com.mj.pojo.RegisterExample;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

@SpringBootTest
class MjApplicationTests {

    /*@Autowired
    UserService userService;*/

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    RegisterMapper registerMapper;

    @Test
    void contextLoads() {
        /*RegisterExample registerExample = new RegisterExample();
        registerExample.createCriteria().andTokenEqualTo("4bd14452-b7f6-4bbe-89ac-94e7ff37033b");
        System.out.println(registerMapper.selectByExample(registerExample));*/
        Question question = new Question();
        question.setId(27);
        question.setTitle("十多块");
        question.setCreator(45);
        question.setTag("滚滚滚");
        question.setDescription("快递费坚实的房间阿斯蒂芬");
//        question.setId();
//        int i = questionMapper.updateByPrimaryKey(question);
        questionMapper.updateByPrimaryKeyWithBLOBs(question);
    }


}
