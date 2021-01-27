package work.mj.com.mj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.mj.com.mj.dao.UserMapper;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

/**
 * 获取用户信息的实体类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        User user = userMapper.selectByPrimaryKey(username);
        return user;
    }
}
