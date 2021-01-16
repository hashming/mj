package work.mj.com.mj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import work.mj.com.mj.dao.UserMapper;
import work.mj.com.mj.pojo.User;
import work.mj.com.mj.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        List<User> list = list(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            // 从设计上来说,一般用户名是唯一的,所以list的大小一般为1
            return list.get(0);
        }
        return null;
    }
}
