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
        User duo = userMapper.selectByPrimaryKey("duo");
        System.out.println(duo);
    }


}
