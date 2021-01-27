package work.mj.com.mj.service;

import work.mj.com.mj.pojo.User;

/**
 * 获取用户信息
 */
public interface UserService{
    User getByUsername(String username);
}
