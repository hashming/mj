package work.mj.com.mj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.mj.com.mj.pojo.User;

/**
 * 获取用户信息
 */
public interface UserService extends IService<User> {
    User getByUsername(String username);
}
