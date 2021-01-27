package work.mj.com.mj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.mj.com.mj.dao.QuestionMapper;
import work.mj.com.mj.dao.RegisterMapper;
import work.mj.com.mj.dao.UserMapper;
import work.mj.com.mj.pojo.QuestionExample;
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
//        System.out.println(questionMapper.selectByPrimaryKey(1));
//        System.out.println(registerMapper.selectByToken("skdfkd"));
        System.out.println(userMapper.selectByPrimaryKey("duo"));
//        System.out.println(questionMapper.selectByExample(new QuestionExample()));
//        System.out.println(questionMapper.selectByPrimaryKey(1));
        System.out.println(questionMapper.selectByExampleWithBLOBs(new QuestionExample()));
    }


}
