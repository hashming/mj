package work.mj.com.mj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.mj.com.mj.dao.UserMapper;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

@SpringBootTest
class MjApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
//        System.out.println(userService.getByUsername("duo"));
        User duo = userMapper.selectByPrimaryKey("duo");
        System.out.println(duo);
    }


}
