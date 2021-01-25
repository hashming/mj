package work.mj.com.mj;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.mj.com.mj.dao.UserMapper;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

import java.util.List;

@SpringBootTest
class MjApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.getByUsername("duo"));
    }


}
