package work.mj.com.mj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.mj.com.mj.pojo.User;

public interface UserService extends IService<User> {
    User getByUsername(String username);
}
